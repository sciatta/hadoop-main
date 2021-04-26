package com.sciatta.dev.java.concurrency.juc.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2021/4/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ThreadPoolShutdown
 */
public class ThreadPoolShutdown {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        
        for (int i = 0; i < 2; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        
        executor.shutdown();
    }
}
