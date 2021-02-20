package com.sciatta.dev.java.database.redis.jedis.failover;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.UUID;

/**
 * Created by yangxiaoyu on 2021/2/18<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FailOverSetGetTests
 */
public class FailOverSetGetTests {
    private String key;
    
    @Before
    public void init() {
        key = UUID.randomUUID().toString();
    }
    
    @Test
    public void testSetGet() {
        FailOverSetGet failOverSetGet = new FailOverSetGet();
        String test = failOverSetGet.set(FailOverSetGet.class.getName(), key);
        assertTrue("ok".equalsIgnoreCase(test));
        
        test = failOverSetGet.get(FailOverSetGet.class.getName());
        assertEquals(key, test);
    }
}
