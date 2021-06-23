package com.sciatta.dev.java.designpattern.creative.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;


/**
 * Created by yangxiaoyu on 2021/6/22<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LazyWithBigLockTests
 */
public class LazyWithBigLockTests {
    
    @Test
    public void testGetInstance() throws InterruptedException {
        final LazyWithBigLock[] instances = new LazyWithBigLock[2];
        CountDownLatch latch = new CountDownLatch(2);
        
        Thread t1 = new Thread(() -> {
            instances[0] = LazyWithBigLock.getInstance();
            latch.countDown();
        });
        
        Thread t2 = new Thread(() -> {
            instances[1] = LazyWithBigLock.getInstance();
            latch.countDown();
        });
        
        t1.start();
        t2.start();
        
        latch.await();
    
        assertSame(instances[0], instances[1]);
    }
}
