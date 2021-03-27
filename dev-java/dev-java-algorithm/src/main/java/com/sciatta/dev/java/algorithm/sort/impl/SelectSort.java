package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;

/**
 * Created by yangxiaoyu on 2021/3/26<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 选择排序
 */
public class SelectSort implements Sort {
    @Override
    public int[] sort(int[] array) {
        int minIndex;
        boolean needSwap = false;
        int temp;
        
        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                    needSwap = true;
                }
            }
            
            if (needSwap) { // 优化，只有当前判断不是最小值，才会交换
                temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                needSwap = false;
            }
            
        }
        
        return array;
    }
}
