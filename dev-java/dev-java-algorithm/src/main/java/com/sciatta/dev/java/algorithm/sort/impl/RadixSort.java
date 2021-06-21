package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;

/**
 * Created by yangxiaoyu on 2021/3/30<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 基数排序：当数据量非常大时，无法通过比较排序（时间复杂度高），也不适用于计数排序（范围过大，如电话号码）；不适用于桶排序（桶的数量有限）；
 * 可以对待排序数组的各位分别进行计数排序
 */
public class RadixSort implements Sort {
    private final int radix;
    
    public RadixSort(int radix) {
        this.radix = radix;
    }
    
    @Override
    public int[] sort(int[] array) {
        if (array.length == 0) return array;
        
        // 求得最大数的位数
        int digits = getMaxDigits(array);
        
        // 从低位开始对数组元素进行计数排序，直到最高位
        for (int i = 1; i <= digits; i++) {
            array = countingSort(array, i);
        }
        
        return array;
    }
    
    private int getMaxDigits(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int digits = 0;
        while (max != 0) {
            digits++;
            max = max / 10;
        }
        
        return digits;
    }
    
    private int[] countingSort(int[] array, int digit) {
        int[] temp = new int[radix];
        int[] sortedArray = new int[array.length];
        
        // 计数
        for (int i = 0; i < array.length; i++) {
            temp[getNumberByDigit(array[i], digit)]++;
        }
        
        // 累加
        for (int i = 1; i < temp.length; i++) {
            temp[i] = temp[i] + temp[i - 1];
        }
        
        // 排序
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[temp[getNumberByDigit(array[i], digit)] - 1] = array[i];
            temp[getNumberByDigit(array[i], digit)]--;
        }
        
        return sortedArray;
    }
    
    private int getNumberByDigit(int e, int digit) {
        int number = 0;
        while (digit != 0) {
            number = e % radix;
            e = e / radix;
            digit--;
        }
        
        return number;
    }
}
