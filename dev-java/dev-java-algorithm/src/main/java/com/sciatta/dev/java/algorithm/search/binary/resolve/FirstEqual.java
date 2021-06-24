package com.sciatta.dev.java.algorithm.search.binary.resolve;

/**
 * Created by yangxiaoyu on 2021/6/24<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 查找第一个值等于给定值的元素
 */
public class FirstEqual {
    public int resolve(int[] array, int length, int search) {
        return doResolve(array, 0, length - 1, search);
    }
    
    private int doResolve(int[] array, int start, int end, int search) {
        if (start > end) {
            return -1;
        }
        
        int mid = start + ((end - start) >> 1); // + - 优先级大于 >>
        
        if (array[mid] == search) {
            // 第一个元素 or 前一个元素不是查找元素
            if (mid == 0 || array[mid - 1] != search) {
                return mid;
            } else {
                // 继续往前查找
                return doResolve(array, start, mid - 1, search);
            }
            
        } else if (array[mid] > search) {
            return doResolve(array, start, mid - 1, search);
        } else {
            return doResolve(array, mid + 1, end, search);
        }
    }
}
