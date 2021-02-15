package com.sciatta.dev.java.database.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * NotSafeThread
 */
public class NotSafeThread {
    private Jedis jedis;    // 不是线程安全的
    private static final String key = "jedis_not_safe_thread";
    private static final int times = 10000;
    private static final int threadNumber = 2;
    
    public NotSafeThread() {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.set(key, "0");
    }
    
    class Worker implements Runnable {
        
        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                jedis.incr(key);
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
        return jedis.get(key);
    }
    
    public static void main(String[] args) throws InterruptedException {
        NotSafeThread notSafe = new NotSafeThread();
        notSafe.run();
    }
}
