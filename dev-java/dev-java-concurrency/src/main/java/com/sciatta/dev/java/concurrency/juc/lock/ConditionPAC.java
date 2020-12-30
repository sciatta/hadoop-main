package com.sciatta.dev.java.concurrency.juc.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangxiaoyu on 2020/11/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 基于Condition实现的生成者和消费者
 */
public class ConditionPAC {
    private Object[] os = new Object[5];
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
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
            lock.lock();
            while (count == os.length) {
                // 满了
                System.out.println("满了");
                notFull.await();
            }
            os[count++] = o;
            System.out.println("放入对象" + o + " 已有对象" + sizeToString(os));
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        try {
            lock.lock();
            while (count == 0) {
                // 空了
                System.out.println("空了");
                notEmpty.await();
            }
            Object o = os[count - 1];
            count--;
            System.out.println("取出对象" + o + " 已有对象" + sizeToString(os));
            notFull.signal();
            return o;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionPAC conditionPAC = new ConditionPAC();
        Random r = new Random();

        // 生产者
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        conditionPAC.put(i);
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
                        conditionPAC.take();
                        Thread.sleep(r.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
