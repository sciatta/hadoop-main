package com.sciatta.dev.java.designpattern.structure.adapter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * QueryTests
 */
public class QueryTests {
    @Test
    public void testFromDataBase() {
        Query query = new QueryFromDataBase(new DataBaseQuery());
        assertEquals("test", query.query("test"));
    }
    
    @Test
    public void testFromRedis() {
        Query query = new QueryFromRedis(new RedisQuery());
        assertEquals("test", query.query("test"));
    }
}
