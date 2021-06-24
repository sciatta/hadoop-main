package com.sciatta.dev.java.algorithm.search.binary.impl;

/**
 * Created by yangxiaoyu on 2021/6/24<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BinarySearch 二分查找法
 */
public class BinarySearch {
    public int resolve(int[] array, int length, int search) {
        return doResolve(array, 0, length - 1, search);
    }
    
    private int doResolve(int[] array, int start, int end, int search) {
        if (start > end) {
            return -1;  // 没有找到
        }
        
        int mid = start + (end - start) / 2; // 防止溢出 (start+end)/2
        
        if (array[mid] == search) {
            return mid;
        } else if (array[mid] > search) {
            return doResolve(array, start, mid - 1, search);
        } else {
            return doResolve(array, mid + 1, end, search);
        }
    }
}
