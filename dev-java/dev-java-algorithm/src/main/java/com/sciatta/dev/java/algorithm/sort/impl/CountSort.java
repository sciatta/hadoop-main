package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;

/**
 * Created by yangxiaoyu on 2021/3/29<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 计数排序，待排序数组中的元素在一个小范围内
 */
public class CountSort implements Sort {
    private final int range;    // [0, range)
    
    public CountSort(int range) {
        this.range = range;
    }
    
    @Override
    public int[] sort(int[] array) {
        int[] countArray = new int[range];
        int[] result = new int[array.length];
        
        // 对待排序数组中的元素累加计数
        for (int e : array) {
            countArray[e]++;
        }
        
        // 计数数组元素和前一个值累加，计算某一个元素前边有多少个元素占位
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] = countArray[i - 1] + countArray[i];
        }
        
        // 根据计数数组占位，对待排序数组排序
        for (int i = array.length - 1; i >= 0; i--) {
            // 倒序遍历对待排序保证稳定性
            result[countArray[array[i]] - 1] = array[i];
            countArray[array[i]]--;
        }
        
        return result;
    }
}
