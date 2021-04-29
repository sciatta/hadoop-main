package com.sciatta.dev.java.concurrency.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangxiaoyu on 2021/4/26<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ReentrantLockAqs
 */
public class ReentrantLockAqs {
    private ReentrantLock lock = new ReentrantLock();
    
    public void run() {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 准备获取锁");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 获取锁");
                
                TimeUnit.SECONDS.sleep(10);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " 准备释放锁");
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放锁");
            }
        }, "t1");
        
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " 准备获取锁");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 获取锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " 准备释放锁");
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放锁");
            }
        }, "t2");
        
        t1.start();
        t2.start();
    }
    
    public static void main(String[] args) {
        ReentrantLockAqs reentrantLockAqs = new ReentrantLockAqs();
        reentrantLockAqs.run();
    }
}
