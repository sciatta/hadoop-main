package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;

/**
 * Created by yangxiaoyu on 2021/3/27<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 归并排序
 */
public class MergeSort implements Sort {
    @Override
    public int[] sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
        
        return array;
    }
    
    private void mergeSort(int[] array, int start, int end) {
        if (start >= end) return;   // 终止条件，单个元素可定有序
        
        int middle = (end + start) / 2;
        
        mergeSort(array, start, middle);   // 左边有序
        mergeSort(array, middle + 1, end);    // 右边有序
        
        merge(array, start, middle, end);   // 两个有序数组合并为一个有序数组
    }
    
    private void merge(int[] array, int start, int middle, int end) {
        int[] temp = new int[end - start + 1]; // 临时有序数组
        
        int leftCursor = start;
        int rightCursor = middle + 1;
        int tempCursor = 0;
        
        while (leftCursor <= middle && rightCursor <= end) {
            if (array[leftCursor] <= array[rightCursor]) {
                temp[tempCursor++] = array[leftCursor++];   // 如果两个有相等元素，先合并左边的，保证稳定性
            } else {
                temp[tempCursor++] = array[rightCursor++];
            }
        }
        
        // 左边遍历完成，合并右边
        if (leftCursor > middle) {
            for (int i = rightCursor; i <= end; i++) {
                temp[tempCursor++] = array[i];
            }
        }
        
        // 右边遍历完成，合并左边
        if (rightCursor > end) {
            for (int i = leftCursor; i <= middle; i++) {
                temp[tempCursor++] = array[i];
            }
        }
        
        // 赋值回原数组
        int p = start;
        tempCursor = 0;
        while (p <= end) {
            array[p++] = temp[tempCursor++];
        }
    }
}
