package com.sciatta.dev.java.zookeeper.example.api;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2020/3/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CuratorTests
 */
public class CuratorTests {
    private static final String CONNECTION = "node01:2181,node02:2181,node03:2181";

    CuratorFramework client;

    private static final int SECOND = 1000;
    private static final int MINUTE = SECOND * 60;

    @Before
    public void init() {
        client = CuratorFrameworkFactory.newClient(CONNECTION, new RetryNTimes(10, 3000));
        client.start();
    }

    @After
    public void destroy() {
        client.close();
    }

    @Test
    public void testCreate() throws Exception {
        // 级联创建目录
        // 第二个参数追加到叶子节点，不会影响父节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/a/b/c", "test".getBytes());
    }

    @Test
    public void testCreateEphemeral() throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/a");
        Thread.sleep(10000);
    }

    @Test
    public void testDelete() throws Exception {
        // 级联删除
        client.delete().deletingChildrenIfNeeded().forPath("/a");
    }

    @Test
    public void testSetData() throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/a");
        client.setData().forPath("/a", "hello".getBytes());
    }

    @Test
    public void testExist() throws Exception {
        Stat stat = client.checkExists().forPath("/a");
        assertNotNull(stat);
    }

    @Test
    public void testGetData() throws Exception {
        byte[] bytes = client.getData().forPath("/a");
        System.out.println(new String(bytes));
    }

    @Test
    public void testGetChildren() throws Exception {
        List<String> strings = client.getChildren().forPath("/");

        System.out.println(strings);
    }

    @Test
    public void testTreeCache() throws Exception {
        // root node 可以不存在
        // 启动监听后，会遍历root node，逐级触发 NODE_ADDED 事件
        // 可以监听所有目录
        TreeCache cache = new TreeCache(client, "/a");

        cache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent event) throws Exception {
                ChildData data = event.getData();

                switch (event.getType()) {
                    case NODE_ADDED:
                    case NODE_REMOVED:
                    case NODE_UPDATED:
                        print(event, data);
                        break;
                }
            }
        });

        monitor(cache);
    }

    @Test
    public void testPathChildrenCache() throws Exception {
        // root node 不存在则自动创建
        // 启动监听后，会遍历root node的所有子node，逐级触发 CHILD_ADDED 事件
        // 只监听root的子node，且root node 不可以删除
        PathChildrenCache cache = new PathChildrenCache(client, "/a", true);

        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                    case CHILD_REMOVED:
                    case CHILD_UPDATED:
                        print(event, event.getData());
                        break;
                }
            }
        });

        monitor(cache);
    }

    private void monitor(PathChildrenCache cache) throws Exception {
        cache.start();

        Thread.sleep(MINUTE * 10);

        cache.close();
    }


    private void monitor(TreeCache cache) throws Exception {
        // 开始监听
        cache.start();

        Thread.sleep(MINUTE * 10);

        // 结束监听
        cache.close();
    }

    private void print(PathChildrenCacheEvent event, ChildData data) {
        doPrint(event.getType().toString(), data);
    }

    private void print(TreeCacheEvent event, ChildData data) {
        doPrint(event.getType().toString(), data);
    }

    private void doPrint(String type, ChildData data) {
        if (data == null) return;

        System.out.println("type=" + type + " path=" + data.getPath() + " data=" + new String(data.getData()));
    }

}
