package com.sciatta.dev.java.algorithm.linear.queue.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/2/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CyclicArrayQueueTests
 */
public class CyclicArrayQueueTests {
    @Test
    public void testFifo() {
        CyclicArrayQueue<Integer> queue = new CyclicArrayQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        
        assertEquals(1, queue.dequeue().intValue());
        assertEquals(2, queue.dequeue().intValue());
        assertEquals(3, queue.dequeue().intValue());
    }
    
    @Test
    public void testCyclic() {
        CyclicArrayQueue<Integer> queue = new CyclicArrayQueue<>(5);
        assertTrue(queue.empty());
        
        assertTrue(queue.enqueue(0));
        assertTrue(queue.enqueue(1));
        assertTrue(queue.enqueue(2));
        assertTrue(queue.enqueue(3));
        
        assertFalse(queue.enqueue(4));  // 最后一个空间不得插入，已满
        assertTrue(queue.full());
        
        assertEquals(0, queue.dequeue().intValue());    // 出队两个元素
        assertEquals(1, queue.dequeue().intValue());
        
        assertTrue(queue.enqueue(4));   // 可入队两个元素
        assertTrue(queue.enqueue(5));
        
        assertFalse(queue.enqueue(6));  // 最后一个空间不得插入，已满
        assertTrue(queue.full());
        
        assertEquals(2, queue.dequeue().intValue());
        assertEquals(3, queue.dequeue().intValue());
        assertEquals(4, queue.dequeue().intValue());
        assertEquals(5, queue.dequeue().intValue());
        assertTrue(queue.empty());
        
        assertNull(queue.dequeue());    // 空队列
    }
}
