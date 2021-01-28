package com.sciatta.dev.java.algorithm.linear.stack.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/1/28<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LinkedListStackTests
 */
public class LinkedListStackTests {
    @Test
    public void testPush() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.push(1);
        assertEquals(1, stack.peek().intValue());
        assertEquals(1, stack.getCount());
        
        stack.push(2);
        assertEquals(2, stack.peek().intValue());
        assertEquals(2, stack.getCount());
        
        stack.push(3);
        assertEquals(3, stack.peek().intValue());
        assertEquals(3, stack.getCount());
    }
    
    @Test
    public void testPop() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        
        assertNull(stack.pop());
        assertNull(stack.peek());
        
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        assertEquals(3, stack.pop().intValue());
        assertEquals(2, stack.getCount());
        
        assertEquals(2, stack.pop().intValue());
        assertEquals(1, stack.getCount());
        
        assertEquals(1, stack.pop().intValue());
        assertEquals(0, stack.getCount());
    
        assertNull(stack.pop());
        assertNull(stack.peek());
    }
}
