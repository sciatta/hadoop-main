package com.sciatta.hadoop.java.example.stream;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2020/12/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ParallelDemo
 */
public class ParallelDemo {
    public static void main(String[] args) {
        int exeNum = 5000000;
        List<String> list = new ArrayList<>(exeNum);
        for (int i = 0; i < exeNum; i++) {
            list.add(UUID.randomUUID().toString());
        }
        
        Collections.shuffle(list);
        serialize(list);
        
        Collections.shuffle(list);
        parallel(list);
    }
    
    private static void serialize(List<String> list) {
        long start = System.nanoTime();
        
        list.stream().sorted().count();
        
        long time = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        
        System.out.println("serialize use: " + time);
    }
    
    private static void parallel(List<String> list) {
        long start = System.nanoTime();
        
        list.parallelStream().sorted().count();
    
        long time = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    
        System.out.println("parallel use: " + time);
    }
}
