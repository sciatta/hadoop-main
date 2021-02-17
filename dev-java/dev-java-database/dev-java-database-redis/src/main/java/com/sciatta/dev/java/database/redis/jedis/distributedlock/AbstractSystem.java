package com.sciatta.dev.java.database.redis.jedis.distributedlock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by yangxiaoyu on 2021/2/16<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * System1
 */
public abstract class AbstractSystem {
    private final DLock dLock;
    private String requestId;
    private int taskExecutionTimes;
    private int taskExecutionSeconds;
    
    public AbstractSystem(String requestId, int taskExecutionTimes, int taskExecutionSeconds) {
        this.dLock = new DLock();
        this.requestId = requestId;
        this.taskExecutionSeconds = taskExecutionSeconds;
        this.taskExecutionTimes = taskExecutionTimes;
    }
    
    public void run() {
        boolean test = dLock.getLock(requestId, true);
        if (test) {
            doTask();
        } else {
            print("[ 很遗憾没有获取到锁，等待锁... ]");
            while (!test) {
                // 没有获取到锁，尝试持续获取锁
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test = dLock.getLock(requestId, true);
            }
            // 保证肯定会获取到锁，不会出现死锁
            doTask();
        }
    }
    
    protected void doTask() {
        print("[ 获取到锁，开始执行任务... ]");
        for (int i = 0; i < taskExecutionTimes; i++) {
            print("开始执行任务第" + (i + 1) + "次");
            try {
                Thread.sleep(taskExecutionSeconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boolean test = dLock.releaseLock(requestId);
        if (test) {
            print("[ 任务执行完毕，释放锁 ]");
        }
    }
    
    private void print(String tip) {
        String data = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        System.out.println(data + " " + this.getClass().getName() + " " + tip);
    }
}
