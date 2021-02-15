package com.sciatta.dev.java.database.redis.api.redisson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Redisson_Map_Tests
 */
public class Redisson_Map_Tests {
    private static final String prefix = "redisson_map_test_";
    private static final String key = prefix + "person";
    
    private RedissonClient redissonClient;
    
    @Before
    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        redissonClient = Redisson.create(config);
    }
    
    @After
    public void destroy() {
        redissonClient.shutdown();
    }
    
    @Test
    public void testPut() {
        RMap<Object, Object> map = redissonClient.getMap(key);
        map.put("name", "rain");
        map.put("age", "18");
    }
    
    @Test
    public void testGet() {
        RMap<String, Object> map = redissonClient.getMap(key);
        Set<String> keys = new HashSet<>();
        keys.add("name");   // 按需查找
        // keys.add("age");
        Map<String, Object> all = map.getAll(keys);
        
        Set<String> keySet = all.keySet();
        assertTrue(keys.contains("name"));
        // assertTrue(keys.contains("age"));
        
        Collection<Object> values = all.values();
        assertTrue(values.contains("rain"));
        // assertTrue(values.contains("18"));
    }
}
