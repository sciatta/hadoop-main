package com.sciatta.dev.java.concurrency.aqs.park;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by yangxiaoyu on 2021/4/27<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ParkWithInterruption park阻塞的时候，如果向这个线程发起中断，这个线程被唤醒，但是不会抛出异常
 */
public class ParkWithInterruption {
    private static boolean interrupted = false;
    
    public Thread start() {
        Thread t = new Thread(() -> {
            while (!interrupted) {
                System.out.println(Thread.currentThread().getName() + " run park...");
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + " receive interrupt command...");
            }
        }, "t");
        
        t.start();
        return t;
    }
    
    public static void main(String[] args) throws InterruptedException {
        ParkWithInterruption park = new ParkWithInterruption();
        Thread t = park.start();
        
        TimeUnit.SECONDS.sleep(3);
        
        interrupted = true;
        t.interrupt();  // 中断park
    }
}
