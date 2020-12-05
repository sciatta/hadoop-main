package com.sciatta.hadoop.java.concurrency.example.sum;

import java.util.concurrent.ExecutionException;

public class UseSynchronized {
    private static Object o = new Object();
    private static int result;

    public static int execute() throws ExecutionException, InterruptedException {
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    result = sum();
                    o.notify(); // 等待or通知 必须和锁定的对象一致
                }
            }
        });
        c.start();

        synchronized (o) {
            while (result == 0) {
                o.wait();
            }
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
