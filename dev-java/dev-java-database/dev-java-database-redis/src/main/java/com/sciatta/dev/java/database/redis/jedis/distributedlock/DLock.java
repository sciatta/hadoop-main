package com.sciatta.dev.java.database.redis.jedis.distributedlock;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * Created by yangxiaoyu on 2021/2/16<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 分布式锁：1、互斥性；2、持有者解锁；3、无死锁；4、锁机制可被多个系统共享
 */
public class DLock {
    private final Jedis jedis;
    private static final String LOCK_NAME = DLock.class.getName();
    private static final String NX = "NX";
    private static final String PX = "PX";
    private static final int DEFAULT_EXPIRED_TIME_SECOND = 30;
    private static final int DEFAULT_EXPIRED_TIME_MILLISECOND = DEFAULT_EXPIRED_TIME_SECOND * 1000;
    
    public DLock() {
        jedis = new Jedis("127.0.0.1", 6379);
    }
    
    public boolean getLock(String requestId) {
        return getLock(requestId, false);
    }
    
    public boolean getLock(String requestId, boolean autoLease) {
        String response = jedis.set(LOCK_NAME, requestId, NX, PX, DEFAULT_EXPIRED_TIME_MILLISECOND);
        boolean test = "ok".equalsIgnoreCase(response);
        if (test && autoLease) {
            // 获取锁成功 && 自动续期
            Thread leaseThread = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(DEFAULT_EXPIRED_TIME_MILLISECOND / 3); // 休息10s
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lease(requestId)) {
                            // 主线程没有退出，续期
                            print("~lease success~");
                        }
                    }
                }
            };
            leaseThread.setDaemon(true);    // 守护线程
            leaseThread.start();
        }
        return test;
    }
    
    public boolean lease(String requestId) {
        Long test = jedis.expire(LOCK_NAME, DEFAULT_EXPIRED_TIME_SECOND);
        return test == 1;
    }
    
    public boolean releaseLock(String requestId) {
        // 保证原子性
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object test = jedis.eval(luaScript, Collections.singletonList(LOCK_NAME), Collections.singletonList(requestId));
        
        return Long.valueOf(1).equals(test);
    }
    
    public Jedis getJedis() {
        return jedis;
    }
    
    private void print(String tip) {
        String data = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        System.out.println(data + " " + this.getClass().getName() + " " + tip);
    }
}
