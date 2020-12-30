package com.sciatta.dev.java.concurrency.foundation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by yangxiaoyu on 2020/11/10<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FutureClass
 */
public class FutureClass {
    static class TaskC implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "Hello, I am Callable!";
        }
    }

    static class TaskR implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask实现了Runnable接口和Future接口
        // Future提供了异步获取线程返回结果的功能
        FutureTask<String> taskC = new FutureTask<>(new TaskC());
        new Thread(taskC).start();
        System.out.println(taskC.get());


        // 也可以传入 Runnable + 放回结果
        FutureTask<String> taskR = new FutureTask<>(new TaskR(), "Hello, I am Runnable");
        new Thread(taskR).start();
        System.out.println(taskR.get());
    }
}
