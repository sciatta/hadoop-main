package com.sciatta.dev.java.designpattern.creative.singleton;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LazyWithDCLTests
 */
public class LazyWithDCLTests {
    @Test
    public void testGetInstance() throws InterruptedException {
        final LazyWithDCL[] instances = new LazyWithDCL[2];
        CountDownLatch latch = new CountDownLatch(2);
        
        Thread t1 = new Thread(() -> {
            instances[0] = LazyWithDCL.getInstance();
            latch.countDown();
        });
        
        Thread t2 = new Thread(() -> {
            instances[1] = LazyWithDCL.getInstance();
            latch.countDown();
        });
        
        t1.start();
        t2.start();
        
        latch.await();
        
        assertSame(instances[0], instances[1]);
    }
    
    static class T {
        
        protected LazyWithDCL getInstance() {
            return LazyWithDCL.getInstance();
        }
        
        public boolean trueOrFalse() {
            LazyWithDCL instance = null;
            
            // instance = LazyWithDCL.getInstance();   // 调用静态方法相当于写死代码，不好测试
            
            instance = getInstance();   // 可以mock调用静态方法的包装方法
            
            return instance != null;
        }
    }
    
    @Test
    public void testTestability() {
        T tt = new T();
        assertTrue(tt.trueOrFalse());
        
        T tf = new T() {
            @Override
            protected LazyWithDCL getInstance() {
                return null;    // 利用多态mock
            }
        };
        assertFalse(tf.trueOrFalse());
    }
}
