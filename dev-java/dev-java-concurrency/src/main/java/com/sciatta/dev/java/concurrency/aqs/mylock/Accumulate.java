package com.sciatta.dev.java.concurrency.aqs.mylock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yangxiaoyu on 2021/4/29<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Accumulate
 */
public class Accumulate {
    private volatile int result;
    private final int threadNum;
    private final int executeNum;
    private final CountDownLatch latch;
    private final MyLock lock = new MyLock();
    
    public Accumulate(int threadNum, int executeNum) {
        this.threadNum = threadNum;
        this.executeNum = executeNum;
        latch = new CountDownLatch(threadNum);
    }
    
    class Worker implements Runnable {
        private final int execute;
        
        public Worker(int execute) {
            this.execute = execute;
        }
        
        @Override
        public void run() {
//            for (int i = 0; i < execute; i++) {
//                lock.lock();
//                try {
//                    result++;
//                } finally {
//                    lock.unlock();
//                }
//            }
            
            // 锁粗化提高性能
            lock.lock();
            try {
                for (int i = 0; i < execute; i++) {
                    result++;
                }
            } finally {
                lock.unlock();
            }
            
            latch.countDown();
        }
    }
    
    public void run() throws InterruptedException {
        Worker worker = new Worker(executeNum);
        
        for (int i = 0; i < threadNum; i++) {
            new Thread(worker, "thread" + (i + 1)).start();
        }
        
        latch.await();
    }
    
    public int getResult() {
        return this.result;
    }
    
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        
        Accumulate accumulate = new Accumulate(10, 1000000);
        accumulate.run();
        System.out.println(accumulate.getResult());
        
        System.out.println(System.currentTimeMillis() - start);
    }
}
