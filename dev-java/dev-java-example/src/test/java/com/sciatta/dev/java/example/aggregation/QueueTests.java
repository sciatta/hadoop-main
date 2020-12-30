package com.sciatta.dev.java.example.aggregation;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Created by yangxiaoyu on 2019-04-25<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * QueueTests <br/>
 * add remove element 会产生异常<br/>
 * offer poll peek
 */
public class QueueTests {
    @Test
    public void testAdd() {
        // add 异常；offer 返回是否成功
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        assertEquals(1, (int) queue.element());
    }

    @Test
    public void testOffer() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(100);
        assertEquals(100, (int) queue.element());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemove() {
        // remove 异常；poll null
        Queue<Integer> queue = new LinkedList<>();
        queue.remove();
    }

    @Test
    public void testPoll() {
        Queue<Integer> queue = new LinkedList<>();
        assertNull(queue.poll());
    }

    @Test(expected = NoSuchElementException.class)
    public void testElement() {
        // element 异常；peek null
        Queue<Integer> queue = new LinkedList<>();
        queue.element();
    }

    @Test
    public void testPeek() {
        Queue<Integer> queue = new LinkedList<>();
        Integer test = queue.peek();
        assertNull(test);
    }
}
