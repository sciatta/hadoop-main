package com.sciatta.dev.java.concurrency.juc.semaphores;

import java.util.concurrent.Semaphore;

/**
 * Created by yangxiaoyu on 2020/11/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SemaphoreInitZero
 */
public class SemaphoreInitZero {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0); // 初始化0个许可

        int p = semaphore.availablePermits();   // 0
        System.out.println(p);

        semaphore.release();    // 调用release 许可+1
        semaphore.release();    // 许可+1
        p = semaphore.availablePermits();
        System.out.println(p);  // 2

        semaphore.acquire();    // 调用acquire 许可-1
        p = semaphore.availablePermits();   // 1
        System.out.println(p);
    }
}
