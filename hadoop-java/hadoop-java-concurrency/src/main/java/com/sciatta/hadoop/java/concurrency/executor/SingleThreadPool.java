package com.sciatta.hadoop.java.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangxiaoyu on 2019-03-31<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * SingleThreadPool
 */
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> System.out.println("AA"));
        executor.execute(() -> System.out.println("BB"));
        executor.execute(() -> System.out.println("CC"));
    }
}
