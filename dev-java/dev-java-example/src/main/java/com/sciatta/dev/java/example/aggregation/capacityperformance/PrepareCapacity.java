package com.sciatta.dev.java.example.aggregation.capacityperformance;

import java.util.ArrayList;

/**
 * Created by yangxiaoyu on 2021/4/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * PrepareCapacity
 */
public class PrepareCapacity {
    public static void main(String[] args) {
        ArrayList<Object> a = new ArrayList<>();
        a.ensureCapacity(GrowCapacity.N);   // 提前分配容量
        
        long start = System.currentTimeMillis();
        
        for (int i = 0; i < GrowCapacity.N; i++) {
            a.add(i);
        }
        
        long end = System.currentTimeMillis();
        
        System.out.println(end - start);
    }
}
