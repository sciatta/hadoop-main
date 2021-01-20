package com.sciatta.dev.java.algorithm.linear.array.jdk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2021/1/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ArrayListPerformanceTests
 */
public class ArrayListPerformanceTests {
    private final int times = 1000000;
    
    @Test
    public void testApplyDefaultCapacity() {
        long start = System.nanoTime();
        
        List<Integer> list = new ArrayList<>(); // 动态扩容
        for (int i = 0; i < times; i++) {
            list.add(i);
        }
    
        printPeriod("applyDefaultCapacity", System.nanoTime() - start);
    }
    
    @Test
    public void testApplyAllCapacity() {
        long start = System.nanoTime();
        
        List<Object> list = new ArrayList<>(times); // 一次性全部申请
        for (int i = 0; i < times; i++) {
            list.add(i);
        }
        printPeriod("applyAllCapacity", System.nanoTime() - start);
    }
    
    private void printPeriod(String tip, long duration) {
        System.out.println(tip + " " + TimeUnit.NANOSECONDS.toMillis(duration) + "ms");
    }
}
