package com.sciatta.dev.java.concurrency.juc.semaphores;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by yangxiaoyu on 2020/11/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 基于Semaphore实现的生成者和消费者
 */
public class SemaphorePAC {
    private int capacity = 5;
    private Object[] os = new Object[capacity];
    private Semaphore lock = new Semaphore(1);
    private Semaphore notEmpty = new Semaphore(0);
    private Semaphore notFull = new Semaphore(capacity);
    private int count;

    private String sizeToString(Object[] os) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < count; i++) {
            sb.append(os[i]);
            if (i == count - 1) {
                return sb.append("]").toString();
            }
            sb.append(", ");
        }

        return sb.append("]").toString();
    }

    public void put(Object o) throws InterruptedException {
        try {
            // 注意：不能先lock.acquire();执行，会出现死锁
            notFull.acquire();  // 占一个位置；如果满了，阻塞
            lock.acquire();
            os[count++] = o;
            System.out.println("放入对象" + o + " 已有对象" + sizeToString(os));
        } finally {
            lock.release();
            notEmpty.release(); // 增加一个许可
        }
    }

    public Object take() throws InterruptedException {
        try {
            notEmpty.acquire();
            lock.acquire();
            Object o = os[count - 1];
            count--;
            System.out.println("取出对象" + o + " 已有对象" + sizeToString(os));
            return o;
        } finally {
            lock.release();
            notFull.release();
        }
    }

    public static void main(String[] args) {
        SemaphorePAC pac = new SemaphorePAC();
        Random r = new Random();

        // 生产者
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        pac.put(i);
                        Thread.sleep(r.nextInt(300));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        // 消费者
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        pac.take();
                        Thread.sleep(r.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
