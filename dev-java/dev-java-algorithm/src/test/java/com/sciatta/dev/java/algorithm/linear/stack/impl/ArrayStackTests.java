package com.sciatta.dev.java.algorithm.linear.stack.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/1/28<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ArrayStackTests
 */
public class ArrayStackTests {
    @Test
    public void testPush() {
        ArrayStack<Integer> as = new ArrayStack<>(5);
        
        assertEquals(5, as.getLength());
        
        as.push(1);
        assertEquals(1, as.peek().intValue());
        assertEquals(5, as.getLength());
        assertEquals(1, as.getCount());
        
        as.push(2);
        assertEquals(2, as.peek().intValue());
        assertEquals(5, as.getLength());
        assertEquals(2, as.getCount());
        
        as.push(3);
        assertEquals(3, as.peek().intValue());
        assertEquals(5, as.getLength());
        assertEquals(3, as.getCount());
        
        as.push(4);
        assertEquals(4, as.peek().intValue());
        assertEquals(5, as.getLength());
        assertEquals(4, as.getCount());
        
        as.push(5);
        assertEquals(5, as.peek().intValue());
        assertEquals(5, as.getLength());
        assertEquals(5, as.getCount());
        
        // expand...
        
        as.push(6);
        assertEquals(6, as.peek().intValue());
        assertEquals(6, as.getCount());
        assertEquals(10, as.getLength());
    }
    
    @Test
    public void testPop() {
        ArrayStack<Integer> as = new ArrayStack<>(5);
        
        assertNull(as.pop());
        assertEquals(0, as.getCount());
        assertEquals(5, as.getLength());
        
        as.push(1);
        as.push(2);
        as.push(3);
        assertEquals(3, as.getCount());
        assertEquals(5, as.getLength());
        
        assertEquals(3, as.pop().intValue());
        assertEquals(2, as.getCount());
        assertEquals(5, as.getLength());
        
        assertEquals(2, as.peek().intValue());
        assertEquals(2, as.getCount());
        assertEquals(5, as.getLength());
    }
}
