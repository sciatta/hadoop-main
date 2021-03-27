package com.sciatta.dev.java.algorithm.sort.impl;

import com.sciatta.dev.java.algorithm.sort.Sort;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/3/27<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BubbleSortTests
 */
public class BubbleSortTests {
    private final Sort sort = new BubbleSort();
    
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
