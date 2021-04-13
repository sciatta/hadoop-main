package com.sciatta.dev.java.example.aggregation;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2021/4/13<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LinkedListTests
 */
public class LinkedListTests {
    @Test
    public void testLinkedList() {
        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        l.addFirst(10);
        
        assertEquals(10, (int) l.getFirst());
        assertEquals(0, (int) l.get(1));
    }
}
