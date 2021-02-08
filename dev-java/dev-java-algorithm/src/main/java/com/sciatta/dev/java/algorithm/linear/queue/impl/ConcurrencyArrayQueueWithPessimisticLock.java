package com.sciatta.dev.java.algorithm.linear.queue.impl;

/**
 * Created by yangxiaoyu on 2021/2/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ConcurrencyArrayQueueWithPessimisticLock
 */
public class ConcurrencyArrayQueueWithPessimisticLock<T> {
    private T[] queue;
    private int capacity;
    private int head, tail;
    
    @SuppressWarnings("unchecked")
    public ConcurrencyArrayQueueWithPessimisticLock(int capacity) {
        this.capacity = capacity;
        this.queue = (T[]) new Object[this.capacity];
        head = tail = 0;    // 初始位置
    }
    
    public synchronized boolean enqueue(T data) {
        if (!full()) {
            queue[tail] = data;
            tail = (tail + 1) % capacity;
            return true;
        }
        
        return false;
    }
    
    public synchronized T dequeue() {
        T ret = null;
        if (!empty()) {
            ret = queue[head];
            
//            try {
//                Thread.sleep(1);   // 并发问题
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            
            head = (head + 1) % capacity;
        }
        
        return ret;
    }
    
    public synchronized boolean empty() {
        return head == tail;
    }
    
    public synchronized boolean full() {
        return (tail + 1) % capacity == head;   // 头尾之间的一个空间浪费，用以标记空间是否已用完
    }
}
