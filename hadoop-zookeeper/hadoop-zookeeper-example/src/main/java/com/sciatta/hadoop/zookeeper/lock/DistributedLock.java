package com.sciatta.hadoop.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yangxiaoyu on 2020/3/7<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * DistributedLock，基于ZooKeeper的分布式锁实现，当无法获得锁时，监听上一个节点，可以有效防止惊群问题
 */
public class DistributedLock implements Watcher {
    private static final int THREAD_NUM = 3;
    private static final String CONNECTION = "node01:2181,node02:2181,node03:2181";
    private static final int SESSION_TIMEOUT = 10000;
    private static final String PARENT_PATH = "/group";
    private static final String CHILD_PATH = "/group/lock";
    private static final CountDownLatch ALL_THREAD = new CountDownLatch(THREAD_NUM);
    private static final int SLEEP_MAX = 1000 * 10;

    private String name;
    private ZooKeeper zk;
    private CountDownLatch waitConnected = new CountDownLatch(1);
    private String waitPath;
    private String currentPath;
    private Random random = new Random();

    public DistributedLock(String name) {
        this.name = name;
    }

    public void createConnection(String connect, int sessionTimeout) throws IOException, InterruptedException {
        // 设置默认 watcher
        zk = new ZooKeeper(connect, sessionTimeout, this);
        printLog("连接等待");
        // 阻塞，异步连接ZooKeeper，等待连接成功通知
        waitConnected.await();
        printLog("object=" + zk.hashCode(), "session=" + zk.getSessionId(), "连接成功");
    }

    public void createParentPath() throws KeeperException, InterruptedException {
        if (zk.exists(PARENT_PATH, false) == null) {
            zk.create(PARENT_PATH, PARENT_PATH.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            printLog("创建父节点", PARENT_PATH);
        } else {
            printLog("父节点已创建");
        }
    }

    public void getLock() throws KeeperException, InterruptedException {
        // 创建临时有序节点
        currentPath = zk.create(CHILD_PATH, CHILD_PATH.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        printLog("创建临时有序节点" + currentPath);

        // 判断当前线程创建的临时节点，是否编号最小；如果是，表示此线程可获得锁
        if (checkMinPath()) {
            getLockSuccess();
        }
    }

    public boolean checkMinPath() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren(PARENT_PATH, false);
        Collections.sort(children);
        printLog("当前节点=" + currentPath, "所有节点=" + children);

        // 当前节点的所在位置
        int index = children.indexOf(currentPath.substring(PARENT_PATH.length() + 1));
        switch (index) {
            case -1: {
                printLog("当前节点=" + currentPath, "不存在, 未获得锁");
                return false;
            }
            case 0: {
                // 第一个
                printLog("当前节点=" + currentPath, "获得锁");
                return true;
            }
            default: {
                // 不是第一个节点，监控前一个临时节点状态
                try {
                    waitPath = PARENT_PATH + "/" + children.get(index - 1);
                    // 当前线程监听上一个节点
                    // 注册监听器，不指定watcher，使用默认监听器
                    zk.getData(waitPath, true, new Stat());
                    printLog("当前节点=" + currentPath, "未获得锁，等待节点=" + waitPath);
                    return false;
                } catch (KeeperException e) {
                    if (zk.exists(waitPath, false) == null) {
                        // 监听上一个节点时，恰好上一个节点被删除，需要重新检测当前节点的顺序
                        printLog("当前节点=" + currentPath, "，等待节点=" + waitPath, "不存在，可能已经被删除，重新检测当前节点");
                        return checkMinPath();
                    } else {
                        throw e;
                    }
                }
            }
        }
    }

    public void getLockSuccess() throws KeeperException, InterruptedException {
        if (zk.exists(this.currentPath, false) == null) {
            printLog("当前节点=" + currentPath, "不存在，虽然获得锁，但不得运行");
            return;
        }

        printLog("当前节点=" + currentPath, "获得锁，处理任务");
        Thread.sleep(random.nextInt(SLEEP_MAX));

        // 删除临时节点
        printLog("当前节点=" + currentPath, "任务结束，释放锁");
        zk.delete(currentPath, -1);

        // 释放zk连接
        if (zk != null) zk.close();

        ALL_THREAD.countDown();
    }

    @Override
    public void process(WatchedEvent event) {
        Event.KeeperState keeperState = event.getState();   // 连接状态，需保证连接状态
        Event.EventType eventType = event.getType();    // 节点处理类型
        String path = event.getPath();

        if (Event.KeeperState.SyncConnected == keeperState) {
            if (Event.EventType.None == eventType) {
                printLog(String.valueOf(zk.getSessionId()), "异步连接成功通知");
                waitConnected.countDown();
            } else if (eventType == Event.EventType.NodeDeleted && path.equals(waitPath)) {
                printLog("当前节点=" + currentPath, " 获得 ", "等待节点=" + waitPath, " 通知");
                try {
                    // 需要重新检测
                    // 1、上一个节点正常退出，当前节点变为第一个节点，会获得锁
                    // 2、上一个节点异常退出，前边还有N个节点没有被处理，会向上一个节点重新注册监听
                    if (checkMinPath()) {
                        getLockSuccess();
                    }
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (Event.KeeperState.Disconnected == keeperState) {
            printLog("与ZK服务器断开连接");
        } else if (Event.KeeperState.AuthFailed == keeperState) {
            printLog("权限检查失败");
        } else if (Event.KeeperState.Expired == keeperState) {
            printLog("会话失效");
        }
    }

    public void printLog(String... infos) {
        StringBuilder sb = new StringBuilder();
        for (String info : infos) {
            sb.append(info);
            sb.append(" ");
        }
        System.out.format("[%s] %s%n", name, sb.toString());
    }

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_NUM; i++) {
            String name = String.valueOf(i + 1);
            new Thread() {
                DistributedLock lock = new DistributedLock(String.valueOf(name));

                @Override
                public void run() {
                    try {
                        // 连接zookeeper集群
                        lock.createConnection(CONNECTION, SESSION_TIMEOUT);
                        // 由一个线程创建持久化父节点
                        synchronized (ALL_THREAD) {
                            lock.createParentPath();
                        }
                        // 获取锁
                        lock.getLock();
                    } catch (Exception e) {
                        lock.printLog(e.getMessage());
                    }
                }
            }.start();
        }
        try {
            // 阻塞当前main线程，等待所有子线程完成；此时，等待线程即使没有无线循环，也不会结束，可以接受到事件通知；
            // 只要当 CountDownLatch 由其他所有线程递减为0后，才会继续向下执行
            ALL_THREAD.await();
            System.out.println("所有线程运行结束!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
