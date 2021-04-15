package com.sciatta.dev.java.concurrency.foundation;

/**
 * Created by yangxiaoyu on 2021/4/14<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * StaticLock<p/>
 * static synchronized 锁的是class对象，synchronized 锁的是对象，互不干扰
 */
public class StaticLock {
    public static synchronized void staticMethod() throws InterruptedException {
        System.out.println("in static method " + Thread.currentThread().getName());
        Thread.sleep(4000);
        System.out.println("out static method " + Thread.currentThread().getName());
    }
    
    public synchronized void method() throws InterruptedException {
        System.out.println("in method " + Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("out method " + Thread.currentThread().getName());
    }
    
    public static void main(String[] args) throws InterruptedException {
        StaticLock staticLock = new StaticLock();
        
        Thread t1 = new Thread(() -> {
            try {
                staticLock.method();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        
        Thread t2 = new Thread(() -> {
            try {
                staticLock.method();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        
        Thread t3 = new Thread(() -> {
            try {
                StaticLock.staticMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");
        
        Thread t4 = new Thread(() -> {
            try {
                StaticLock.staticMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4");
        
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}
