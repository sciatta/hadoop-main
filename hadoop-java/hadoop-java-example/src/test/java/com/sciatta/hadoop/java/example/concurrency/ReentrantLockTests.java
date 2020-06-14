package com.sciatta.hadoop.java.example.concurrency;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangxiaoyu on 2019-03-16<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ReentrantLockTests
 */
public class ReentrantLockTests {
    @Test
    public void testLockState() {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        lock.lock();
        assertEquals(2, lock.getHoldCount());

        assertTrue(lock.isHeldByCurrentThread());

        lock.unlock();
        assertEquals(1, lock.getHoldCount());

        lock.unlock();
        assertEquals(0, lock.getHoldCount());

        assertFalse(lock.isHeldByCurrentThread());
    }
}
