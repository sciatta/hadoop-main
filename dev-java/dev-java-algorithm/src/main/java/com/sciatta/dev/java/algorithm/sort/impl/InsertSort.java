package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;

/**
 * Created by yangxiaoyu on 2021/3/26<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 插入排序
 */
public class InsertSort implements Sort {
    @Override
    public int[] sort(int[] array) {
        int insertIndex = 0;
        boolean needSwap = false;
        int swapElement;
    
        /**
         * 假设第一个元素处于有序数组中，遍历后续数组元素，逐个插入到前边的有序数组中
         * | 5 | 8, 3, 1, 6|
         */
        for (int i = 1; i < array.length; i++) {
            swapElement = array[i];
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > swapElement) {
                    array[j + 1] = array[j];
                    insertIndex = j;
                    needSwap = true;
                }
            }
            if (needSwap) {
                array[insertIndex] = swapElement;
                needSwap = false;
            }
        }
        
        return array;
    }
}
