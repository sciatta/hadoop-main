package com.sciatta.dev.java.database.redis.jedis.distributedlock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.UUID;

/**
 * Created by yangxiaoyu on 2021/2/16<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DLockTests
 */
public class DLockTests {
    @Before
    public void init() {
        DLock dLock = new DLock();
        dLock.getJedis().flushDB();
    }
    
    @Test
    public void testGetLock() {
        String uuid = UUID.randomUUID().toString();
        DLock dLock = new DLock();
        boolean res = dLock.getLock(uuid);
        assertTrue(res);
    }
    
    @Test
    public void testAutoLease() throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        DLock dLock = new DLock();
        boolean res = dLock.getLock(uuid, true);
        assertTrue(res);
        
        Thread.sleep(1000 * 30);
    }
    
    @Test
    public void testMutex() {
        boolean res;
        
        String u1 = UUID.randomUUID().toString();
        DLock l1 = new DLock();
        res = l1.getLock(u1);
        assertTrue(res);
        
        String u2 = UUID.randomUUID().toString();
        DLock l2 = new DLock();
        res = l2.getLock(u2);
        assertFalse(res);
    }
    
    @Test
    public void testLease() throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        DLock dLock = new DLock();
        boolean res = dLock.getLock(uuid);
        assertTrue(res);
        
        Thread.sleep(10000);
        
        boolean lease = dLock.lease(uuid);
        assertTrue(lease);
    }
    
    @Test
    public void testReleaseLock() {
        String uuid = UUID.randomUUID().toString();
        DLock dLock = new DLock();
        boolean res = dLock.getLock(uuid);
        assertTrue(res);
        
        boolean test = dLock.releaseLock(uuid);
        assertTrue(test);
    }
    
    @Test
    public void testNotOwner() {
        String uuid = UUID.randomUUID().toString();
        DLock dLock = new DLock();
        boolean res = dLock.getLock(uuid);
        assertTrue(res);
        
        
        boolean xxxxx = dLock.releaseLock("xxxxx");
        assertFalse(xxxxx);
    }
}
