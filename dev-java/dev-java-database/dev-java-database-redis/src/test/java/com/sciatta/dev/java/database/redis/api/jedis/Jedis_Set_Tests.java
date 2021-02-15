package com.sciatta.dev.java.database.redis.api.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Jedis_Set_Tests
 */
public class Jedis_Set_Tests {
    private Jedis jedis;
    private static final String prefix = "jedis_set_test_";
    private static final String key = prefix + "number";
    
    @Before
    public void init() {
        this.jedis = new Jedis("127.0.0.1", 6379);
    }
    
    @After
    public void detroy() {
        jedis.flushDB();
    }
    
    @Test
    public void testAddd() {
        Long result = jedis.sadd(key, "1", "2", "3", "2");
        assertEquals(3, result.longValue());
        
        Set<String> smembers = jedis.smembers(key);
        assertEquals(3, smembers.size());
        assertTrue(smembers.contains("1"));
        assertTrue(smembers.contains("2"));
        assertTrue(smembers.contains("3"));
        
        assertFalse(smembers.contains("4"));
    }
    
    @Test
    public void testInter() {
        jedis.sadd(prefix + "a", "1", "2", "3");
        jedis.sadd(prefix + "b", "2", "4");
        
        Set<String> set = jedis.sinter(prefix + "a", prefix + "b");
        assertEquals(1, set.size());
        assertTrue(set.contains("2"));
    }
    
    @Test
    public void testUnion() {
        jedis.sadd(prefix + "a", "1", "2", "3");
        jedis.sadd(prefix + "b", "2", "4");
        
        Set<String> set = jedis.sunion(prefix + "a", prefix + "b");
        assertEquals(4, set.size());
        assertTrue(set.contains("1"));
        assertTrue(set.contains("2"));
        assertTrue(set.contains("3"));
        assertTrue(set.contains("4"));
    }
    
    @Test
    public void testDiff() {
        jedis.sadd(prefix + "a", "1", "2", "3");
        jedis.sadd(prefix + "b", "2", "4");
        
        Set<String> set = jedis.sdiff(prefix + "a", prefix + "b");
        assertEquals(2, set.size());
        assertTrue(set.contains("1"));
        assertTrue(set.contains("3"));
    }
}
