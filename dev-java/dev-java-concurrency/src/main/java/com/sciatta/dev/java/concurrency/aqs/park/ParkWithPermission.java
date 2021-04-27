package com.sciatta.dev.java.concurrency.aqs.park;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by yangxiaoyu on 2021/4/27<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ParkWithPermission park默认情况下是没有许可的，所以直接调用park的话，会阻塞；可以提起调用unpark，归还许可，此时park的话，消费许可，不会阻塞立即返回
 */
public class ParkWithPermission {
    private boolean unPark = false;
    
    public Thread startT1() {
        Thread t1 = new Thread(() -> {
            int i = 0;
            while (!unPark) {
                i++;
                System.out.println(Thread.currentThread().getName() + " 执行第 " + i + " 次");
                // 默认情况下是没有许可的，所以直接调用park的话，会阻塞
                // LockSupport.park();
                
                // 第一次归还许可
                if (i == 1)
                    LockSupport.unpark(Thread.currentThread());
                // 第一次获得许可不会阻塞；第二次会阻塞
                LockSupport.park();
            }
        }, "t1");
        
        t1.start();
        return t1;
    }
    
    public void startT2(Thread t1) {
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                
                unPark = true;
                LockSupport.unpark(t1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        t2.start();
    }
    
    public static void main(String[] args) {
        ParkWithPermission park = new ParkWithPermission();
        Thread t1 = park.startT1();
        park.startT2(t1);
    }
}
