package com.sciatta.dev.java.database.redis.api.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Jedis_SortedSet_Tests
 */
public class Jedis_SortedSet_Tests {
    private Jedis jedis;
    private static final String prefix = "jedis_sorted_set_test_";
    private static final String key = prefix + "ss";
    
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
        jedis.zadd(key, 99, "a");   // 2
        jedis.zadd(key, 98, "b");   // 1
        jedis.zadd(key, 97, "c");   // 0
        
        assertEquals(3, jedis.zcard(key).longValue());
        
        Set<String> set = jedis.zrange(key, 0, 1);
        assertEquals(2, set.size());
        
        assertTrue(set.contains("c"));
        assertTrue(set.contains("b"));
        assertFalse(set.contains("a"));
    }
    
    @Test
    public void testRange() {
        jedis.zadd(key, 96, "f");
        jedis.zadd(key, 98, "b");
        jedis.zadd(key, 99, "a");
        jedis.zadd(key, 97, "c");
        
        Set<String> set = jedis.zrange(key, 0, -1); // 从小到大排序
        assertEquals(4, set.size());
        String[] array = set.toArray(new String[]{});
        assertEquals("f", array[0]);
        assertEquals("c", array[1]);
        assertEquals("b", array[2]);
        assertEquals("a", array[3]);
        
        set = jedis.zrevrange(key, 0, -1);  // 从大到小排序
        assertEquals(4, set.size());
        array = set.toArray(new String[]{});
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("f", array[3]);
        
    }
}
