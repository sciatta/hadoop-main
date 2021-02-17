package com.sciatta.dev.java.database.redis.jedis.counter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yangxiaoyu on 2021/2/17<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LimitTimes
 */
public class LimitTimes {
    private JedisPool pool;
    private String key;
    private int second;
    private int limitTimes;
    
    public LimitTimes(String key, int second, int limitTimes) {
        this.key = key;
        this.second = second;
        this.limitTimes = limitTimes;
        this.pool = new JedisPool("localhost", 6379);
    }
    
    public boolean access() {
        Jedis jedis = null;
        Long incr;
        try {
            jedis = pool.getResource();
            incr = jedis.incr(key); // 第一次访问，如果没有key，则初始key值为0，然后自增1 => 1
            
            if (incr == 1) {
                // 首次访问设置过期时间
                jedis.expire(key, this.second);
            }
        } finally {
            if (jedis != null) jedis.close();
        }
        return incr <= limitTimes;   // 到达限制次数
    }
    
    public static void main(String[] args) throws InterruptedException {
        int threadNum = 10;
        LimitTimes service = new LimitTimes(LimitTimes.class.getName(), 5, 6);
        CountDownLatch latch = new CountDownLatch(threadNum);
        
        Thread[] threads = new Thread[threadNum];
        
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    int i = 0;
                    while (i < 3) {
                        boolean test = service.access();
                        if (test) {
                            print("can access~~~");
                            i++;
                        } else {
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    latch.countDown();
                }
            };
            threads[i].start();
        }
        
        latch.await();
    }
    
    private static void print(String tip) {
        String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        System.out.println(data + " " + Thread.currentThread().getName() + " " + tip);
    }
}
