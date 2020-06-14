package com.sciatta.hadoop.java.example.concurrency.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by yangxiaoyu on 2019-03-23<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ThreadPoolWithRunnable
 */
public class ThreadPoolWithRunnable {
    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> System.out.println("1"));
        executor.execute(() -> System.out.println("2"));
        executor.execute(() -> System.out.println("3"));
    }
}
