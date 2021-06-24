package com.sciatta.dev.java.algorithm.search.binary.resolve;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/24<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FirstEqualTests
 */
public class FirstEqualTests {
    private FirstEqual resolve = new FirstEqual();
    
    @Test
    public void testFirst() {
        int index = this.resolve.resolve(new int[]{1, 1, 2, 8, 8, 8, 9, 9}, 8, 1);
        assertEquals(0, index);
    }
    
    @Test
    public void testLast() {
        int index = this.resolve.resolve(new int[]{1, 1, 2, 8, 8, 8, 9, 9}, 8, 9);
        assertEquals(6, index);
    }
    
    @Test
    public void testFirstEqual() {
        int index = this.resolve.resolve(new int[]{1, 1, 2, 8, 8, 8, 9, 9}, 8, 8);
        assertEquals(3, index);
    }
}
