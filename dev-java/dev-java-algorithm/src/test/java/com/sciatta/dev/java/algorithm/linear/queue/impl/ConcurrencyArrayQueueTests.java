package com.sciatta.dev.java.algorithm.linear.queue.impl;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2021/2/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ConcurrencyArrayQueueTests
 */
public class ConcurrencyArrayQueueTests {
    int capacity = 100000;
    int data = 10;
    
    @Test
    public void testNoLock() throws InterruptedException {
        ConcurrencyArrayQueueWithNoLock<Integer> queue = new ConcurrencyArrayQueueWithNoLock<>(capacity);
        for (int i = 0; i < data; i++) {
            queue.enqueue(i);
        }
        
        Thread[] ts = new Thread[data];
        for (int i = 0; i < data; i++) {
            ts[i] = new Thread() {
                @Override
                public void run() {
                    Integer test = queue.dequeue();
                    System.out.println(Thread.currentThread().getName() + " dequeue: " + test);
                }
            };
            ts[i].setName(String.valueOf(i));
            ts[i].start();
        }
        
        for (int i = 0; i < data; i++) {
            ts[i].join();
        }
    }
    
    @Test
    public void testPessimistic() throws InterruptedException {
        ConcurrencyArrayQueueWithPessimisticLock<Integer> queue = new ConcurrencyArrayQueueWithPessimisticLock<>(capacity);
        
        for (int i = 0; i < data; i++) {
            queue.enqueue(i);
        }
        
        Thread[] ts = new Thread[data];
        for (int i = 0; i < data; i++) {
            ts[i] = new Thread() {
                @Override
                public void run() {
                    Integer test = queue.dequeue();
                    System.out.println(Thread.currentThread().getName() + " dequeue: " + test);
                }
            };
            ts[i].setName(String.valueOf(i));
            ts[i].start();
        }
        
        for (int i = 0; i < data; i++) {
            ts[i].join();
        }
    }
    
    @Test
    public void testCAS() throws InterruptedException {
        ConcurrencyArrayQueueWithCAS<Integer> queue = new ConcurrencyArrayQueueWithCAS<>(capacity);
        
        for (int i = 0; i < data; i++) {
            queue.enqueue(i);
        }
        
        Thread[] ts = new Thread[data];
        for (int i = 0; i < data; i++) {
            ts[i] = new Thread() {
                @Override
                public void run() {
                    Integer test = queue.dequeue();
                    System.out.println(Thread.currentThread().getName() + " dequeue: " + test);
                }
            };
            ts[i].setName(String.valueOf(i));
            ts[i].start();
        }
        
        for (int i = 0; i < data; i++) {
            ts[i].join();
        }
    }
}
