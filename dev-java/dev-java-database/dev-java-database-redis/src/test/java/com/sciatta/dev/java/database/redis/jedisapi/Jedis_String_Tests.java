package com.sciatta.dev.java.database.redis.jedisapi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import redis.clients.jedis.Jedis;

import java.util.Locale;
import java.util.Set;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Jedis_String_Tests
 */
public class Jedis_String_Tests {
    private Jedis jedis;
    private static final String prefix = "jedis_string_test_";
    
    @Before
    public void init() {
        this.jedis = new Jedis("127.0.0.1", 6379);
    }
    
    @Test
    public void testSetString() {
        String s = jedis.set(prefix + "str", "100");
        assertEquals("ok".toUpperCase(Locale.ROOT), s);
        
        jedis.set(prefix + "str1", "101");
        jedis.set(prefix + "str2", "102");
    }
    
    @Test
    public void testDelString() {
        // 存在
        Long s = jedis.del(prefix + "str");
        assertEquals(1, s.longValue());
        
        // 不存在
        s = jedis.del(prefix + "str1");
        assertEquals(0, s.longValue());
    }
    
    @Test
    public void testGetString() {
        // 存在
        String s = jedis.get(prefix + "str");
        assertEquals("100", s);
        
        // 不存在
        s = jedis.get(prefix + "str1");
        assertNull(s);
    }
    
    @Test
    public void testKeys() {
        Set<String> keys = jedis.keys(prefix + "str");
        assertEquals(1, keys.size());
        
        keys = jedis.keys(prefix + "str*");
        assertEquals(3, keys.size());
    }
    
    @Test
    public void testIncr() {
        Long incr = jedis.incr(prefix + "str");
        assertEquals(101, incr.longValue());
    }
    
    @Test
    public void testDecr() {
        Long decr = jedis.decr(prefix + "str");
        assertEquals(100, decr.longValue());
    }
    
    @Test
    public void testIncrBy() {
        Long aLong = jedis.incrBy(prefix + "str", 3);
        assertEquals(103, aLong.longValue());
    }
    
    @Test
    public void testDecrBy() {
        Long aLong = jedis.decrBy(prefix + "str", 3);
        assertEquals(100, aLong.longValue());
    }
    
    @Test
    public void testExists() {
        Boolean exists = jedis.exists(prefix + "str");
        assertTrue(exists);
    }
    
    @Test
    public void testFlush() {
        String s = jedis.flushDB(); // 清空数据库
        assertEquals("ok".toUpperCase(Locale.ROOT), s);
    }
    
    @Test
    public void testDbSize() {
        Long aLong = jedis.dbSize();    // 数据库中记录数
        assertEquals(3, aLong.longValue());
    }
}
