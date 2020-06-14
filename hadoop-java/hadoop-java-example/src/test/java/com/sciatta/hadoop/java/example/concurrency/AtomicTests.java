package com.sciatta.hadoop.java.example.concurrency;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangxiaoyu on 2019-04-01<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * AtomicTests
 */
public class AtomicTests {
    @Test
    public void testInteger() {
        AtomicInteger i = new AtomicInteger(0);

        assertEquals(1, i.addAndGet(1));
        assertEquals(1, i.getAndSet(2));
        assertEquals(2, i.get());

        assertTrue(i.compareAndSet(2, 3));
        assertEquals(3, i.get());

        //和期望不符
        assertFalse(i.compareAndSet(2, 3));

        assertEquals(3, i.getAndUpdate(x -> x + 5));    // 一元操作
        assertEquals(8, i.get());

        // 有初始值参与运算
        assertEquals(8, i.getAndAccumulate(1, (prev, x) -> prev - x));  // 二元操作
        assertEquals(7, i.get());
    }
}
