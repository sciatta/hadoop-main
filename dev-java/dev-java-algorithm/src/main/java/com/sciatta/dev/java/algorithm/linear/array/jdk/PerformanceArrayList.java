package com.sciatta.dev.java.algorithm.linear.array.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2021/1/26<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * PerformanceArrayList
 */
public class PerformanceArrayList {
    private final int times = 1000000;
    
    public void applyDefaultCapacity() {
        long start = System.nanoTime();
        
        List<Integer> list = new ArrayList<>(); // 动态扩容
        for (int i = 0; i < times; i++) {
            list.add(i);
        }
        
        printPeriod("applyDefaultCapacity", System.nanoTime() - start);
    }
    
    public void applyAllCapacity() {
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
    
    public static void main(String[] args) {
        PerformanceArrayList performanceArrayList = new PerformanceArrayList();
        
        // 动态扩容
        performanceArrayList.applyDefaultCapacity();
        
        // 一次性申请
        performanceArrayList.applyAllCapacity();
    }
}
