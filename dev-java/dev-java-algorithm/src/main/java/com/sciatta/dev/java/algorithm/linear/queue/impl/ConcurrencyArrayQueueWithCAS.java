package com.sciatta.dev.java.algorithm.linear.queue.impl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangxiaoyu on 2021/2/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ConcurrencyArrayQueueWithCAS 当线程较多时，就会出现过多无谓的自旋，导致资源浪费
 */
public class ConcurrencyArrayQueueWithCAS<T> {
    private T[] queue;
    private int capacity;
    private volatile AtomicInteger head = new AtomicInteger(0);
    private volatile AtomicInteger tail = new AtomicInteger(0);
    
    @SuppressWarnings("unchecked")
    public ConcurrencyArrayQueueWithCAS(int capacity) {
        this.capacity = capacity;
        this.queue = (T[]) new Object[this.capacity];
    }
    
    public boolean enqueue(T data) {
        while (true) { // 自旋
            int expect = tail.get();
            int update = (tail.get() + 1) % capacity;
            
            if (full()) {
                return false;   // 不阻塞，直接返回
            }
            
            if (tail.compareAndSet(expect, update)) {
                queue[expect] = data;
                return true;
            }
        }
    }
    
    public T dequeue() {
        while (true) {  // 自旋
            int expect = head.get();
            int update = (head.get() + 1) % capacity;
            
//            try {
//                Thread.sleep(1);   // 测试并发问题
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            
            if (empty()) {
                return null;    // 不阻塞，直接返回
            }
            
            if (head.compareAndSet(expect, update)) {
                return queue[expect];
            } else {
                // System.out.println(Thread.currentThread().getName() + " 自旋");
            }
        }
    }
    
    public boolean empty() {
        return head.get() == tail.get();
    }
    
    public boolean full() {
        return (tail.get() + 1) % capacity == head.get();   // 头尾之间的一个空间浪费，用以标记空间是否已用完
    }
}
