package com.sciatta.dev.java.concurrency.foundation.interrupt;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangxiaoyu on 2021/4/26<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * InterruptUseLock
 */
public class InterruptUseLock {
    private ReentrantLock lock = new ReentrantLock();
    
    public void run() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1" + " 准备获得锁");
                lock.lock();
                System.out.println("t1" + " 获得锁");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("t1" + " 释放锁");
            }
        });
        
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(50);
                System.out.println("t2" + " 准备获得锁");
                // lock.lock();    // 不可中断
                lock.lockInterruptibly();   // 等待锁的过程中可以被中断
                System.out.println("t2" + " 获得锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("t2" + " 释放锁");
            }
        });
        
        t1.start();
        t2.start();
        
        Thread.sleep(3000);
        t2.interrupt(); // 尝试中断t2
    }
    
    public static void main(String[] args) throws InterruptedException {
        InterruptUseLock lock = new InterruptUseLock();
        lock.run();
    }
}
