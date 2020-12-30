package com.sciatta.dev.bigdata.zookeeper.example.api;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/3/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * APITests
 */
public class APITests {
    private ZooKeeper zk;
    private static final String CONNECTION = "node01:2181,node02:2181,node03:2181";
    private static final int SESSION_TIMEOUT = 30000;

    @Before
    public void connect() throws IOException {
        zk = new ZooKeeper(CONNECTION, SESSION_TIMEOUT, null);
    }

    @After
    public void close() throws InterruptedException {
        zk.close();
    }

    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        // 不可创建级联目录
        String test = zk.create("/test", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(test);
    }

    @Test
    public void testCreateEphemeral() throws KeeperException, InterruptedException {
        String test = zk.create("/test", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(10000);
        System.out.println(test);
    }

    @Test
    public void testDelete() throws KeeperException, InterruptedException {
        // 指定删除版本
        // 不可级联删除
        zk.delete("/test", 0);
    }

    @Test
    public void testSetData() throws KeeperException, InterruptedException {
        zk.setData("/test", "t1".getBytes(), 2);
    }

    @Test
    public void testExist() throws KeeperException, InterruptedException {
        // 不包括节点的内容数据
        Stat exists = zk.exists("/test", false);

        System.out.println(exists);
    }

    @Test
    public void testGetData() throws KeeperException, InterruptedException {
        // 连接node02
        byte[] data = zk.getData("/test", false, null);
        System.out.println(zk);
        System.out.println(new String(data));

        // 手动关闭node02
        Thread.sleep(10000);

        // 仍然可以正常返回，但自动切换到连接node03
        data = zk.getData("/test1", false, null);
        System.out.println(zk);
        System.out.println(new String(data));
    }

    @Test
    public void testGetChild() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren("/", false);

        for (String c : children) {
            System.out.println(c);
        }
    }

    @Test
    public void testGetDataAddWatcher() throws KeeperException, InterruptedException {
        // watcher只能被触发一次
        Stat exists = zk.exists("/test", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.format("state:%s type:%s path:%s", watchedEvent.getState(), watchedEvent.getType(), watchedEvent.getPath());
            }
        });

        if (exists == null) {
            System.out.println("not exists");
        } else {
            zk.setData("/test", "a".getBytes(), -1);
            zk.setData("/test", "b".getBytes(), -1);
        }
    }
}
