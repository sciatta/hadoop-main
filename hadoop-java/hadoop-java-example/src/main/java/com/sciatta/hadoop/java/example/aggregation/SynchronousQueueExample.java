package com.sciatta.hadoop.java.example.aggregation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by yangxiaoyu on 2019-05-22<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * SynchronousQueue没有容量概念，消费者线程等待生产者线程，反之亦然。线程间传递数据，握手后，一起离开
 */
public class SynchronousQueueExample {
    public static void main(String[] args) throws InterruptedException {
        // true 公平，由队列实现
        // false 由堆栈实现，默认
        BlockingQueue<String> queue = new SynchronousQueue<>(true);

        class Producer extends Thread {

            @Override
            public void run() {
                int i = 0;
                // 判断线程是否中断
                while (!isInterrupted()) {
                    try {
                        i++;
                        String result = "" + Thread.currentThread().getId() + i;
                        System.out.println("[" + Thread.currentThread().getId() + "] 生产阻塞：" + result);
                        queue.put(result);
                        System.out.println("[" + Thread.currentThread().getId() + "] 生产完成：" + result);
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        // 抛出异常后会清除线程的中断状态
                        System.err.println(e);
                        // 再次设置线程中断转态
                        this.interrupt();
                    }
                }
            }
        }

        class Consumer extends Thread {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        System.out.println("[" + Thread.currentThread().getId() + "] 消费阻塞");
                        String result = queue.take();
                        System.out.println("[" + Thread.currentThread().getId() + "] 消费完成：" + result);
                    } catch (InterruptedException e) {
                        System.err.println(e);
                        this.interrupt();
                    }
                }
            }
        }

        Producer p1 = new Producer();

        Consumer c1 = new Consumer();

        p1.start();

        c1.start();

        Thread.sleep(5);

        // 设置线程的中断状态
        p1.interrupt();

        c1.interrupt();
    }
}
