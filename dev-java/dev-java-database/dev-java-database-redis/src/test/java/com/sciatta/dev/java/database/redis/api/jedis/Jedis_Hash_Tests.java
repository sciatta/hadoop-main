package com.sciatta.dev.java.database.redis.api.jedis;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Jedis_Hash_Tests
 */
public class Jedis_Hash_Tests {
    private Jedis jedis;
    private static final String prefix = "jedis_hash_test_";
    private static final String key = prefix + "person";
    
    @Before
    public void init() {
        this.jedis = new Jedis("127.0.0.1", 6379);
    }
    
    @Test
    public void testAdd() {
        jedis.hset(key, "name", "rain");
        jedis.hset(key, "age", "18");
    }
    
    @Test
    public void testGet() {
        String name = jedis.hget(key, "name");
        assertEquals("rain", name);
    }
    
    @Test
    public void testGetAll() {
        Map<String, String> all = jedis.hgetAll(key);
        assertEquals(2, all.size());
    }
    
    @Test
    public void testGetKeys() {
        Set<String> hkeys = jedis.hkeys(key);
        
        assertEquals(2, hkeys.size());
        assertTrue(hkeys.contains("name"));
        assertTrue(hkeys.contains("age"));
    }
    
    @Test
    public void testGetValues() {
        List<String> hvals = jedis.hvals(key);
        assertEquals(2, hvals.size());
        assertTrue(hvals.contains("rain"));
        assertTrue(hvals.contains("18"));
    }
}
