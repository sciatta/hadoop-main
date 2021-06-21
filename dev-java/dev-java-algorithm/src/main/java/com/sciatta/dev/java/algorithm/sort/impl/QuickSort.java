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
        // 递归终止条件
        if (start >= end) return;
        
        // 分区，中轴和两边已经基本有序
        int pivotIndex = partition(array, start, end);
        
        // 左边排序
        quickSort(array, start, pivotIndex - 1);
        
        // 右边排序
        quickSort(array, pivotIndex + 1, end);
    }
    
    protected int partition(int[] array, int start, int end) {
        int pivot = selectPivot(array, start, end);    // 选择最后一个元素为中轴
        int temp;
        
        // 原地排序
        // endPivotIndex是左边元素小于中轴元素的位置
        int endPivotIndex = start;
        for (int i = start; i < end; i++) {
            if (array[i] <= array[pivot]) {
                temp = array[endPivotIndex];
                array[endPivotIndex] = array[i];
                array[i] = temp;
    
                endPivotIndex++;
            }
        }
        
        // 交换中轴元素
        temp = array[pivot];
        array[pivot] = array[endPivotIndex];
        array[endPivotIndex] = temp;
        
        return endPivotIndex;
    }
    
    private int selectPivot(int[] array, int start, int end) {
        return end;
    }
}
