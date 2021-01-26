package com.sciatta.dev.java.algorithm.linear.linked.resolve;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/1/21<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ReverseTests
 */
public class ReverseTests {
    @Test
    public void testReverse() {
        Reverse<Integer> reverse = new Reverse<>(new Integer[]{1, 2, 3, 4, 5});
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, reverse.toArray());
        
        assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, reverse.doReverse());
    }
    
    @Test
    public void testEmptyLinkedList() {
        Reverse<Integer> reverse = new Reverse<>(new Integer[]{});
        assertNull(reverse.doReverse());
    }
    
    @Test
    public void testOneElementLinkedList() {
        Reverse<Integer> reverse = new Reverse<>(new Integer[]{1});
        assertArrayEquals(new Integer[]{1}, reverse.doReverse());
    }
}
