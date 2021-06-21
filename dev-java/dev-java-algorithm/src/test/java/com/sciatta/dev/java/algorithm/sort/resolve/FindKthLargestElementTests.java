package com.sciatta.dev.java.algorithm.sort.resolve;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/21<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FindKthLargestElementTests
 */
public class FindKthLargestElementTests {
    private FindKthLargestElement service = new FindKthLargestElement();
    
    @Test(expected = IllegalArgumentException.class)
    public void testMoreThanArrayLength() {
        service.resolve(getArray(), 7);
    }
    
    @Test
    public void testNumberOne() {
        int test = service.resolve(getArray(), 6);
        assertEquals(9, test);
    }
    
    @Test
    public void testSmallestOne() {
        int test = service.resolve(getArray(), 1);
        assertEquals(1, test);
    }
    
    @Test
    public void test3thLargestElement() {
        int test = service.resolve(getArray(), 3);
        assertEquals(5, test);
    }
    
    private int[] getArray() {
        return new int[]{7, 5, 1, 9, 2, 6};
    }
}
