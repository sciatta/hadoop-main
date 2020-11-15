package com.sciatta.hadoop.java.concurrency.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yangxiaoyu on 2020/11/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ReentrantReadWriteDemo
 */
public class ReentrantReadWriteDemo {
    static class Count {
        private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        public void get() throws InterruptedException {
            rwLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get begin");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " get end");
            } finally {
                rwLock.readLock().unlock();
            }
        }

        public void put() throws InterruptedException {
            rwLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " put begin");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " put end");
            } finally {
                rwLock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Count count = new Count();

        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        count.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        count.put();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
