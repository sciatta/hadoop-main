package com.sciatta.dev.java.algorithm.linear.queue.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/2/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LinkedListQueueTests
 */
public class LinkedListQueueTests {
    @Test
    public void testEnqueue() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        assertTrue(queue.empty());
        
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        
        assertFalse(queue.empty());
    }
    
    @Test
    public void testDequeue() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertFalse(queue.full());
        
        assertEquals(1, queue.dequeue().intValue());
        assertEquals(2, queue.dequeue().intValue());
        assertEquals(3, queue.dequeue().intValue());
        
        assertNull(queue.dequeue());
        assertTrue(queue.empty());
    }
}
