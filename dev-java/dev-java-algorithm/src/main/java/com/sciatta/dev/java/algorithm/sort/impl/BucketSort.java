package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;

import java.util.*;

/**
 * Created by yangxiaoyu on 2021/3/29<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 桶排序，计数排序的特例，当待排序数组元素范围很大时，开辟范围大小的计数数组开销很大
 */
public class BucketSort implements Sort {
    private final int bucketNum;
    private Sort sort;
    
    public BucketSort(int bucketNum) {
        this.bucketNum = bucketNum;
        this.sort = new QuickSort();
    }
    
    @Override
    public int[] sort(int[] array) {
        // 指定桶的个数
        Object[] buckets = new Object[bucketNum];
        
        if (array.length == 0) {
            return array;
        }
        
        // 确定每个桶的范围区间
        int min = array[0];
        int max = array[0];
        for (Integer e : array) {
            if (e > max) {
                max = e;
            }
            
            if (e < min) {
                min = e;
            }
        }
        int range = (max - min) / bucketNum + 1;
        
        // 将待排序数组元素装入桶中，桶间保持有序性
        for (int e : array) {
            int index = (e - min) / range;
            Object bucket = buckets[index];
            if (bucket == null) {
                buckets[index] = new ArrayList<Integer>();
            }
            ((ArrayList<Integer>) buckets[index]).add(e);
        }
        
        // 每个桶中的元素快速排序，之后逐个桶遍历其中元素即可获得有序数组
        int i = 0;
        for (Object bucket : buckets) {
            if (bucket != null) {
                int[] temp = ((ArrayList<Integer>) bucket).stream().mapToInt(Integer::intValue).toArray();
                sort.sort(temp);
                
                for (Integer e : temp) {
                    array[i++] = e;
                }
            }
        }
        
        return array;
    }
}
