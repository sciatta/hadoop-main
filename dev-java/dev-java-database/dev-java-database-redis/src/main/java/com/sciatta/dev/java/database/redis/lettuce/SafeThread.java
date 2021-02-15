package com.sciatta.dev.java.database.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.concurrent.*;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * SafeThread
 */
public class SafeThread implements Runnable, AutoCloseable {
    private final RedisClient client;
    private final StatefulRedisConnection<String, String> connect;  // 线程安全
    private final ExecutorService service;
    private final CountDownLatch latch;
    private static final int threadNum = 5;
    private static final int times = 10000;
    private static final String key = "lettuce_safe_thread";
    
    public SafeThread() {
        client = RedisClient.create("redis://localhost");
        connect = client.connect();
        
        service = Executors.newFixedThreadPool(threadNum);
        latch = new CountDownLatch(threadNum);
        
        RedisCommands<String, String> sync = connect.sync();
        sync.set(key, "0");
    }
    
    @Override
    public void run() {
        for (int i = 0; i < threadNum; i++) {
            service.execute(new Worker());
        }
    }
    
    class Worker implements Runnable {
        
        @Override
        public void run() {
            RedisCommands<String, String> sync = connect.sync();
            for (int i = 0; i < times; i++) {
                sync.incr(key);
            }
            latch.countDown();
        }
    }
    
    public String getResult() {
        RedisCommands<String, String> sync = connect.sync();
        return sync.get(key);
    }
    
    @Override
    public void close() {
        connect.close();
        client.shutdown();
        service.shutdown();
    }
    
    public void waitFinish() throws InterruptedException {
        latch.await();
    }
    
    public static void main(String[] args) {
        try (SafeThread safeThread = new SafeThread()) {
            safeThread.run();
            safeThread.waitFinish();
            System.out.println("result is: " + safeThread.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
