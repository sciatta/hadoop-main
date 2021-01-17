package com.sciatta.dev.java.algorithm.linear.array.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2021/1/17<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ArrayListDemo
 */
public class ArrayListDemo {
    
    public void applyDefaultCapacity(int times) {
        List<Integer> list = new ArrayList<>(); // 动态扩容
        for (int i = 0; i < times; i++) {
            list.add(i);
        }
    }
    
    public void applyAllCapacity(int times) {
        List<Object> list = new ArrayList<>(times); // 一次性全部申请
        for (int i = 0; i < times; i++) {
            list.add(i);
        }
    }
    
    public void printPeriod(String tip, long duration) {
        System.out.println(tip + " " + TimeUnit.NANOSECONDS.toMillis(duration));
    }
    
    public static void main(String[] args) {
        int times = 1000000;
        ArrayListDemo demo = new ArrayListDemo();
        
        long start = System.nanoTime();
        demo.applyDefaultCapacity(times);
        demo.printPeriod("applyDefaultCapacity", System.nanoTime() - start);
    
        start = System.nanoTime();
        demo.applyAllCapacity(times);
        demo.printPeriod("applyAllCapacity", System.nanoTime() - start);
    }
}
