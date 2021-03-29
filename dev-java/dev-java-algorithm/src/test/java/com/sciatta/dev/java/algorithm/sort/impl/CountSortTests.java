package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2021/3/29<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CountSortTests
 */
public class CountSortTests {
    private final Sort sort = new CountSort(10);
    
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
}
