package com.sciatta.dev.java.algorithm.linear.queue.impl;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2021/2/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BlockedArrayQueueTests
 */
public class BlockedArrayQueueTests {
    @Test
    public void testEnqueue() {
        BlockedArrayQueue<Integer> queue = new BlockedArrayQueue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
    }
    
    @Test(expected = BlockedArrayQueue.BlockedArrayQueueException.class)
    public void testEnqueueInFullQueue() {
        BlockedArrayQueue<Integer> queue = new BlockedArrayQueue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
    }
    
    @Test(expected = BlockedArrayQueue.BlockedArrayQueueException.class)
    public void testDequeueInEmptyQueue() {
        BlockedArrayQueue<Integer> queue = new BlockedArrayQueue<>(3);
        queue.dequeue();
    }
    
    @Test
    public void testEnqueueAndDequeue() throws InterruptedException {
        BlockedArrayQueue<Integer> queue = new BlockedArrayQueue<>(3);
        
        // 消费者
        Thread consumer = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Integer i = queue.dequeue();
                    System.out.println(Thread.currentThread().getName() + " dequeue:" + i);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        
        // 生产者
        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    queue.enqueue(i);
                    System.out.println(Thread.currentThread().getName() + " enqueue:" + i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        
        consumer.setName("consumer");
        consumer.start();
        producer.setName("producer");
        producer.start();
        
        consumer.join();
        producer.join();
    }
}
