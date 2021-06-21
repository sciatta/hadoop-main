package com.sciatta.dev.java.algorithm.sort.resolve;

import com.sciatta.dev.java.algorithm.sort.impl.QuickSort;

/**
 * Created by yangxiaoyu on 2021/6/21<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 求第K大元素，利用快速排序，时间复杂度是 O(n)
 */
public class FindKthLargestElement extends QuickSort {
    public int resolve(int[] array, int kthLargest) {
        if (kthLargest > array.length) {
            throw new IllegalArgumentException("find " + kthLargest + "thLargest element more than the length of array that is "
                    + array.length);
        }
        
        return quickSort(array, 0, array.length - 1, kthLargest);
    }
    
    private int quickSort(int[] array, int start, int end, int kthLargest) {
        int pivotIndex = partition(array, start, end);
        
        int actualKthLargest = pivotIndex + 1;
        
        if (actualKthLargest == kthLargest) {
            return array[pivotIndex];
        } else if (actualKthLargest < kthLargest) {
            return quickSort(array, pivotIndex + 1, end, kthLargest);
        } else {
            return quickSort(array, start, pivotIndex - 1, kthLargest);
        }
    }
}