package com.sciatta.hadoop.java.concurrency.juc.semaphores;

import java.util.concurrent.Semaphore;

/**
 * Created by yangxiaoyu on 2020/11/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SemaphoreWithSerial
 */
public class SemaphoreWithSerial {
    public static void main(String[] args) {
        int permits = 3;
        int threadNum = 5;
        Semaphore semaphore = new Semaphore(permits);

        class Task implements Runnable {

            @Override
            public void run() {
                try {
                    semaphore.acquire(permits); // 一次性获得所有许可，退化为串行
                    System.out.println(Thread.currentThread().getName() + " 获得许可");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " 释放许可");
                    semaphore.release(permits);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Task()).start();
        }

    }
}
