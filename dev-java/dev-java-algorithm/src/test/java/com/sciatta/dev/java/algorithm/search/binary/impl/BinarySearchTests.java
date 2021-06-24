package com.sciatta.dev.java.algorithm.search.binary.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/24<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BinarySearchTests
 */
public class BinarySearchTests {
    private BinarySearch search = new BinarySearch();
    
    @Test
    public void testBinary() {
        int index = search.resolve(new int[]{1, 2, 3, 4, 5}, 5, 3);
        assertEquals(2, index);
    }
    
    @Test
    public void testFirst() {
        int index = search.resolve(new int[]{1, 2, 3, 4, 5}, 5, 1);
        assertEquals(0, index);
    }
    
    @Test
    public void testLast() {
        int index = search.resolve(new int[]{1, 2, 3, 4, 5}, 5, 5);
        assertEquals(4, index);
    }
    
    @Test
    public void testNotExist() {
        int index = search.resolve(new int[]{1, 2, 3, 4, 5}, 5, 7);
        assertEquals(-1, index);
    }
    
    @Test
    public void testEmptyArray() {
        int index = search.resolve(new int[]{}, 0, 7);
        assertEquals(-1, index);
    }
}
