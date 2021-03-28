package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;

/**
 * Created by yangxiaoyu on 2021/3/27<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 快速排序
 */
public class QuickSort implements Sort {
    @Override
    public int[] sort(int[] array) {
        
        quickSort(array, 0, array.length - 1);
        
        return array;
    }
    
    private void quickSort(int[] array, int start, int end) {
        if (start >= end) return;
        
        // 分区
        int pivotIndex = partition(array, start, end);
        
        // 左边排序
        quickSort(array, start, pivotIndex - 1);
        
        // 右边排序
        quickSort(array, pivotIndex + 1, end);
    }
    
    private int partition(int[] array, int start, int end) {
        int pivot = selectPivot(array, start, end);    // 选择最后一个元素为中轴
        int temp;
        
        // 原地排序
        int exchange = start;
        for (int i = start; i < end; i++) {
            if (array[i] <= array[pivot]) {
                temp = array[exchange];
                array[exchange] = array[i];
                array[i] = temp;
                
                exchange++;
            }
        }
        
        // 中轴设置
        temp = array[pivot];
        array[pivot] = array[exchange];
        array[exchange] = temp;
        
        return exchange;
    }
    
    private int selectPivot(int[] array, int start, int end) {
        return end;
    }
}
