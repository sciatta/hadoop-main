package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;

/**
 * Created by yangxiaoyu on 2021/3/26<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 冒泡排序
 */
public class BubbleSort implements Sort {
    
    @Override
    public int[] sort(int[] array) {
        int temp;
        boolean swap = false;
        
        for (int i = 0; i < array.length - 1; i++) { // 控制轮数
            for (int j = 0; j < (array.length - i - 1); j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swap = true;
                }
            }
            
            if (!swap) {
                // 没有交换，说明已有序
                break;
            }
            
        }
        
        return array;
    }
}
