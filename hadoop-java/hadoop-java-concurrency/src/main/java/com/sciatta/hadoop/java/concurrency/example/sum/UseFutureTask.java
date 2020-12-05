package com.sciatta.hadoop.java.concurrency.example.sum;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UseFutureTask {

    public static int execute() throws ExecutionException, InterruptedException {
        int result = 0;

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });

        new Thread(task).start();
        result = task.get();

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
