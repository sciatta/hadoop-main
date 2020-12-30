package com.sciatta.dev.java.concurrency.example.sum;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class UseCyclicBarrier {
    // 在每个被调用线程处阻塞，直到所有阻塞的线程满足parties，然后所有线程同时退出阻塞状态继续执行
    private static CyclicBarrier barrier = new CyclicBarrier(2);
    private static int result;
    
    public static int execute() throws InterruptedException {
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum();
                try {
                    Thread.sleep(2000);
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " execute");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        c.start();
        
        try {
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " execute");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        
        int result = execute(); // 这是得到的返回值
        
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        
        // 然后退出main线程
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
