package com.sciatta.dev.java.concurrency.juc.semaphores;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by yangxiaoyu on 2020/11/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CyclicBarrierDemo
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int parties = 3;
        CyclicBarrier barrier = new CyclicBarrier(parties, new Runnable() {
            // 由最后一个进入barrier的线程执行
            @Override
            public void run() {
                System.out.println("All Slave finish: " + Thread.currentThread().getName());
            }
        });


        class Task implements Runnable {
            private CyclicBarrier barrier;

            public Task(CyclicBarrier barrier) {
                this.barrier = barrier;
            }

            @Override
            public void run() {
                System.out.println("Slave: " + Thread.currentThread().getName());
                try {
                    // 子线程阻塞
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < parties; i++) {
            new Thread(new Task(barrier)).start();
        }
    }
}
