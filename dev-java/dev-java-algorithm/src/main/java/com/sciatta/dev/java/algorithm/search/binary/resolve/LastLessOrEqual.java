package com.sciatta.dev.java.algorithm.search.binary.resolve;

/**
 * Created by yangxiaoyu on 2021/6/24<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 查找最后一个小于等于给定值的元素
 */
public class LastLessOrEqual {
    public int resolve(int[] array, int length, int search) {
        return doResolve(array, 0, length - 1, length, search);
    }
    
    private int doResolve(int[] array, int start, int end, int length, int search) {
        if (start > end) return -1;
        
        int mid = start + ((end - start) >> 1);
        
        if (array[mid] <= search) {
            if (mid == (length - 1) || array[mid + 1] > search)
                return mid;
            
            return doResolve(array, mid + 1, end, length, search);
        } else {
            return doResolve(array, start, mid - 1, length, search);
        }
    }
}
