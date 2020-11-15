package com.sciatta.hadoop.java.concurrency.juc.lock;

/**
 * Created by yangxiaoyu on 2020/11/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * DeadLock
 */
public class DeadLock {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private void doNothing() {
    }

    public void startThread1() {
        new Thread() {
            @Override
            public void run() {
                synchronized (lock1) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        doNothing();
                    }
                }
            }
        }.start();
    }

    public void startThread2() {
        new Thread() {
            @Override
            public void run() {
                synchronized (lock2) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        doNothing();
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        DeadLock lock = new DeadLock();
        lock.startThread1();
        lock.startThread2();
    }
}
