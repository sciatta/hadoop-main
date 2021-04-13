package com.sciatta.dev.java.example.aggregation.capacityperformance;

import java.util.ArrayList;

/**
 * Created by yangxiaoyu on 2021/4/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * GrowCapacity
 */
public class GrowCapacity {
    public static final int N = 10000000;
    
    public static void main(String[] args) {
        ArrayList<Object> a = new ArrayList<>();
        
        long start = System.currentTimeMillis();
        
        for (int i = 0; i < N; i++) {
            a.add(i);
        }
        
        long end = System.currentTimeMillis();
        
        System.out.println(end - start);
    }
}
