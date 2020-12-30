package com.sciatta.dev.java.concurrency.juc.semaphores;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yangxiaoyu on 2020/11/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CountdownLatchDemo
 */
public class CountdownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int coreThread = 5;
        CountDownLatch latch = new CountDownLatch(coreThread);

        class Task implements Runnable {
            private CountDownLatch latch;

            public Task(CountDownLatch latch) {
                this.latch = latch;
            }

            @Override
            public void run() {
                System.out.println("Slave: " + Thread.currentThread().getName());
                latch.countDown();
            }
        }


        for (int i = 0; i < coreThread; i++) {
            new Thread(new Task(latch)).start();
        }

        // 主线程阻塞
        latch.await();
        System.out.println("Master: " + Thread.currentThread().getName());
    }
}
