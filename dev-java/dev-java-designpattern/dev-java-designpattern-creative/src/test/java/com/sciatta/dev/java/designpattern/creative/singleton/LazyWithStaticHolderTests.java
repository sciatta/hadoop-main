package com.sciatta.dev.java.designpattern.creative.singleton;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertSame;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LazyWithStaticHolderTests
 */
public class LazyWithStaticHolderTests {
    @Test
    public void testGetInstance() throws InterruptedException {
        final LazyWithStaticHolder[] instances = new LazyWithStaticHolder[2];
        CountDownLatch latch = new CountDownLatch(2);
    
        Thread t1 = new Thread(() -> {
            instances[0] = LazyWithStaticHolder.getInstance();
            latch.countDown();
        });
    
        Thread t2 = new Thread(() -> {
            instances[1] = LazyWithStaticHolder.getInstance();
            latch.countDown();
        });
    
        t1.start();
        t2.start();
    
        latch.await();
    
        assertSame(instances[0], instances[1]);
    }
}
