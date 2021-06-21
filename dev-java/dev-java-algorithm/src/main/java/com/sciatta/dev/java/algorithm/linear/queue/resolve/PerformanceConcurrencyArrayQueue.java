package com.sciatta.dev.java.algorithm.linear.queue.resolve;

import com.sciatta.dev.java.algorithm.linear.queue.impl.ConcurrencyArrayQueueWithCAS;
import com.sciatta.dev.java.algorithm.linear.queue.impl.ConcurrencyArrayQueueWithPessimisticLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2021/2/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * PerformanceConcurrencyArrayQueue
 */
public class PerformanceConcurrencyArrayQueue {
    // 30 -- 如果线程数比较多的话，CAS性能不如悲观锁，CPU被打满，自旋浪费CPU资源，使得其他线程得不到运行
    // 8 -- 线程并发度适中的情况下，CAS并发性能由于悲观锁
    private static final int threadNum = 8;
    
    private static final int elementNum = 3000000;
    private static final int capacity = elementNum * threadNum;
    
    private static void pessimisticLock() throws InterruptedException {
        ConcurrencyArrayQueueWithPessimisticLock<Integer> queue = new ConcurrencyArrayQueueWithPessimisticLock<>(capacity);
        
        Thread[] tin = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            tin[i] = new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < elementNum; j++) {
                        queue.enqueue(j);
                    }
                }
            };
            tin[i].start();
        }
        
        Thread[] tout = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            tout[i] = new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < elementNum; j++) {
                        queue.dequeue();
                    }
                }
            };
            tout[i].start();
        }
        
        for (int i = 0; i < threadNum; i++) {
            tin[i].join();
            tout[i].join();
        }
    }
    
    private static void cas() throws InterruptedException {
        ConcurrencyArrayQueueWithCAS<Integer> queue = new ConcurrencyArrayQueueWithCAS<>(capacity);
        
        Thread[] tin = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            tin[i] = new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < elementNum; j++) {
                        queue.enqueue(j);
                    }
                }
            };
            tin[i].start();
        }
        
        Thread[] tout = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            tout[i] = new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < elementNum; j++) {
                        queue.dequeue();
                    }
                }
            };
            tout[i].start();
        }
        
        for (int i = 0; i < threadNum; i++) {
            tin[i].join();
            tout[i].join();
        }
    }
    
    private static void printPeriod(String tip, long duration) {
        System.out.println(tip + " " + TimeUnit.NANOSECONDS.toMillis(duration) + "ms");
    }
    
    public static void main(String[] args) throws InterruptedException {
        // 悲观锁
        long start = System.nanoTime();
        pessimisticLock();
        printPeriod("pessimisticLock", System.nanoTime() - start);
        
        // 乐观锁
        start = System.nanoTime();
        cas();
        printPeriod("cas", System.nanoTime() - start);
    }
}
