package com.sciatta.hadoop.java.concurrency.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by yangxiaoyu on 2019-03-25<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ThreadPoolWithCallable
 */
public class ThreadPoolWithCallable {
    private static void submit(ExecutorService service, String content) {
        // callable的输出内容会被包装成为future
        Future<String> f1 = service.submit(() -> {
            Thread.sleep(5000);
            return content;
        });
        try {
            // 等待输出，当前线程阻塞
            String result = f1.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        submit(service, "one");
        submit(service, "two");
        submit(service, "three");
    }
}
