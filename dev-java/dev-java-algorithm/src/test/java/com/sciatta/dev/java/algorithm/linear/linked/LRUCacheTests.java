package com.sciatta.dev.java.algorithm.linear.linked;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/1/18<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LRUCacheTests
 */
public class LRUCacheTests {
    private final int capacity = 5;
    private final LRUCache<Integer, Integer> cache = new LRUCache<>(capacity);
    
    @Before
    public void init() {
        for (int i = 0; i < capacity; i++) {
            cache.put(i, i);
        }
        assertArrayEquals(new Integer[]{4, 3, 2, 1, 0}, cache.keySnapshot());
    }
    
    @Test
    public void testPutCache() {
        cache.put(5, 5);
        assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, cache.keySnapshot());
    }
    
    @Test
    public void testGetCache() {
        Integer v = cache.get(3);
        assertEquals(Integer.valueOf(3), v);
        assertArrayEquals(new Integer[]{3, 4, 2, 1, 0}, cache.keySnapshot());
        
        v = cache.get(0);
        assertEquals(Integer.valueOf(0), v);
        assertArrayEquals(new Integer[]{0, 3, 4, 2, 1}, cache.keySnapshot());
        
        v = cache.get(9);
        assertEquals(null, v);
    }
}
