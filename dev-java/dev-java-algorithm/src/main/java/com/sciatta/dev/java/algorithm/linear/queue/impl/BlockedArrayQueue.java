package com.sciatta.dev.java.algorithm.linear.queue.impl;

/**
 * Created by yangxiaoyu on 2021/2/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BlockedArrayQueue
 */
public class BlockedArrayQueue<T> extends CyclicArrayQueue<T> {
    private final Object lock;
    private final int retry;
    
    public BlockedArrayQueue(int capacity) {
        super(capacity);
        lock = new Object();
        retry = 3;
    }
    
    @Override
    public boolean enqueue(T data) {
        int i = 0;
        synchronized (lock) {
            while (!super.enqueue(data)) {
                // 满 -> 当前线程阻塞
                try {
                    lock.wait(1000);    // 阻塞&释放锁；等待1s后如果没有获得通知，则会重新获得锁
                    if (i++ < retry) {
                        System.out.println("enqueue retry time: " + i + " current data is: " + data);
                    } else {
                        throw new InterruptedException("enqueue retry time: " + i + " current data is: " + data);
                    }
                } catch (InterruptedException e) {
                    throw new BlockedArrayQueueException("the queue is full, not allow to enqueue");
                }
            }
            lock.notify();
        }
        return true;
    }
    
    @Override
    public T dequeue() {
        T ret;
        int i = 0;
        synchronized (lock) {
            while ((ret = super.dequeue()) == null) {
                // 空 -> 当前线程阻塞
                try {
                    lock.wait(1000);
                    if (i++ < retry) {
                        System.out.println("dequeue retry time: " + i + " current data is: " + ret);
                    } else {
                        throw new InterruptedException("dequeue retry time: " + i + " current data is: " + ret);
                    }
                } catch (InterruptedException e) {
                    throw new BlockedArrayQueueException("the queue is empty, not allow to dequeue");
                }
            }
            lock.notify();
        }
        return ret;
    }
    
    static class BlockedArrayQueueException extends RuntimeException {
    
        private static final long serialVersionUID = -2611127523125605679L;
    
        public BlockedArrayQueueException() {
        }
    
        public BlockedArrayQueueException(String message) {
            super(message);
        }
    
        public BlockedArrayQueueException(String message, Throwable cause) {
            super(message, cause);
        }
    
        public BlockedArrayQueueException(Throwable cause) {
            super(cause);
        }
    
        public BlockedArrayQueueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
