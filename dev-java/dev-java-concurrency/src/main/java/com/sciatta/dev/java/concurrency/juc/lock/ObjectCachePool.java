package com.sciatta.dev.java.concurrency.juc.lock;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangxiaoyu on 2020/11/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ObjectCachePool基于semaphore
 */
public class ObjectCachePool<T> {
    public interface ObjectFactory<T> {
        T makeObject();
    }

    class Node {
        T obj;
        Node next;
    }

    final int capacity; // 限制缓存池容量
    final ObjectFactory<T> factory;
    final Lock lock = new ReentrantLock();
    final Semaphore semaphore;
    private Node head;
    private Node tail;

    public ObjectCachePool(int capacity, ObjectFactory<T> factory) {
        this.capacity = capacity;
        this.factory = factory;
        this.semaphore = new Semaphore(this.capacity);
        this.head = null;
        this.tail = null;
    }

    public T getObject() throws InterruptedException {
        semaphore.acquire();    // 获取信号量，当到达容量限制时，阻塞；信号量可获取时，恢复
        return getNextObject();
    }

    private T getNextObject() {
        lock.lock();
        try {
            if (head == null) {
                return factory.makeObject();    // 新创建
            } else {
                // 已有则出队
                Node ret = head;
                head = head.next;
                if (head == null) tail = null;
                ret.next = null;    // help GC
                return ret.obj;
            }
        } finally {
            lock.unlock();
        }
    }

    private void returnObjectToPool(T t) {
        lock.lock();
        try {
            // 入队
            Node node = new Node();
            node.obj = t;
            if (tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }

        } finally {
            lock.unlock();
        }
    }

    public void returnObject(T t) {
        returnObjectToPool(t);
        semaphore.release();    // 释放信号量
    }

    public static void main(String[] args) {
        ObjectCachePool<Object> cache = new ObjectCachePool<>(3, Object::new);
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Object o = cache.getObject();
                        System.out.println(getName() + " get " + o + " from pool");

                        Thread.sleep(random.nextInt(1000));

                        System.out.println(getName() + " put " + o + " to pool");
                        cache.returnObject(o);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
