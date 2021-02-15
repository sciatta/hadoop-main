package com.sciatta.dev.java.database.redis.lettuceapi;

import io.lettuce.core.LettuceStrings;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/2/15<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Lettuce_String_Tests
 */
public class Lettuce_String_Tests {
    RedisClient redisClient;
    StatefulRedisConnection<String, String> connect;
    private static final String prefix = "lettuce_string_test_";
    private static final String key = prefix + "hello";
    
    @Before
    public void init() {
        redisClient = RedisClient.create("redis://localhost");
        connect = redisClient.connect();
    }
    
    @After
    public void destroy() {
        connect.close();
        redisClient.shutdown();
    }
    
    @Test
    public void testSet() {
        RedisCommands<String, String> command = connect.sync(); // 同步连接
        command.set(key, "world");
    }
    
    @Test
    public void testGet() {
        RedisCommands<String, String> command = connect.sync();
        String test = command.get(key);
        assertEquals("world", test);
    }
}
