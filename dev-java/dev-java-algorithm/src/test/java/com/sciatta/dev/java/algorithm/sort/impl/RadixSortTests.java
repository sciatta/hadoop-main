package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2021/3/30<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * RadixSortTests
 */
public class RadixSortTests {
    private final Sort sort = new RadixSort(10);
    
    @Test
    public void testEmptyArray() {
        int[] result = sort.sort(new int[]{});
        assertEquals(0, result.length);
    }
    
    @Test
    public void testOneElement() {
        int[] result = sort.sort(new int[]{1});
        assertEquals(1, result.length);
    }
    
    @Test
    public void testArray() {
        int[] result = sort.sort(new int[]{5, 8, 3, 1, 6});
        assertArrayEquals(new int[]{1, 3, 5, 6, 8}, result);
    }
    
    @Test
    public void testMultiDigit() {
        int[] result = sort.sort(new int[]{54, 87, 31, 1, 666, 5});
        assertArrayEquals(new int[]{1, 5, 31, 54, 87, 666}, result);
    }
}
