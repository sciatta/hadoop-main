package com.sciatta.dev.java.database.redis.api.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Jedis_List_Tests
 */
public class Jedis_List_Tests {
    private Jedis jedis;
    private static final String prefix = "jedis_list_test_";
    private static final String key = prefix + "zoo";
    
    @Before
    public void init() {
        this.jedis = new Jedis("127.0.0.1", 6379);
    }
    
    @After
    public void detroy() {
        jedis.flushDB();
    }
    
    @Test
    public void testAddToLeft() {
        jedis.lpush(key, "tiger");
        jedis.lpush(key, "pander");
        jedis.lpush(key, "dog");
        jedis.lpush(key, "cat");
        assertEquals(4, jedis.llen(key).longValue());
        
        assertEquals("cat", jedis.lpop(key));
        assertEquals("dog", jedis.lpop(key));
        assertEquals("pander", jedis.lpop(key));
        assertEquals("tiger", jedis.lpop(key));
        
        Long llen = jedis.llen(key);
        assertEquals(0, llen.longValue());
    }
    
    @Test
    public void testAddToRight() {
        jedis.rpush(key, "cat");
        jedis.rpush(key, "dog");
        jedis.rpush(key, "fish");
        
        List<String> lrange = jedis.lrange(key, 0, 1);
        assertEquals("cat", lrange.get(0));
        assertEquals("dog", lrange.get(1));
        
        assertEquals("fish", jedis.rpop(key));
        assertEquals("dog", jedis.rpop(key));
        assertEquals("cat", jedis.rpop(key));
        
        assertEquals(0, jedis.llen(key).longValue());
    }
}
