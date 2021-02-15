package com.sciatta.dev.java.database.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * NotSafeThread
 */
public class NotSafeThread {
    private JedisPool jp;    // jedis不是线程安全的，需要池化
    private static final String key = "jedis_not_safe_thread";
    private static final int times = 10000;
    private static final int threadNumber = 5;
    
    public NotSafeThread() {
        jp = new JedisPool("127.0.0.1", 6379);
        Jedis resource = jp.getResource();
        try {
            resource.set(key, "0"); // 初始化
        } finally {
            resource.close();
        }
    }
    
    class Worker implements Runnable {
        
        @Override
        public void run() {
            Jedis resource = jp.getResource();
            try {
                for (int i = 0; i < times; i++) {
                    resource.incr(key);
                }
            } finally {
                resource.close();
            }
        }
    }
    
    public void run() throws InterruptedException {
        Thread[] threads = new Thread[threadNumber];
        
        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
        
        for (int i = 0; i < threadNumber; i++) {
            threads[i].join();
        }
    }
    
    public String getResult() {
        Jedis resource = jp.getResource();
        String result;
        try {
            result = resource.get(key);
        } finally {
            resource.close();
        }
        
        return result;
    }
    
    public static void main(String[] args) throws InterruptedException {
        NotSafeThread notSafe = new NotSafeThread();
        notSafe.run();
        System.out.println("result is: " + notSafe.getResult());
    }
}
