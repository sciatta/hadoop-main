package com.sciatta.hadoop.java.concurrency.example.sum;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseLock {
    private static int result;
    private static Lock lock = new ReentrantLock();
    private static Condition hasResult = lock.newCondition();   // 线程间协作

    public static int execute() throws ExecutionException, InterruptedException {
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    result = sum();
                    hasResult.signal();
                    try {
                        Thread.sleep(4000); // 发出信号，但延迟4s释放锁，另一个线程会延迟4s重新获得锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        c.start();

        lock.lock();
        try {
            System.out.println("第一次获取到锁");
            while (result == 0) {
                hasResult.await();
                System.out.println("第二次获取到锁");
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

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
