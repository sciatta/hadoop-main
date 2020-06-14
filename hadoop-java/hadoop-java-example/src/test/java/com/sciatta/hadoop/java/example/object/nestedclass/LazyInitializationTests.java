package com.sciatta.hadoop.java.example.object.nestedclass;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-02-27<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * LazyInitializationTests
 */
public class LazyInitializationTests {
    @Test
    public void testEcho() {
        // 静态类不调用，不会加载
        assertEquals("hello", LazyInitialization.echo("hello"));
    }

    @Test
    public void testGetSimpleHolder() {
        LazyInitialization.Holder simpleHolder = LazyInitialization.getSimpleHolder();
        assertNotNull(simpleHolder);

        LazyInitialization.Holder otherHolder = LazyInitialization.getSimpleHolder();
        assertNotSame(simpleHolder, otherHolder);
    }

    @Test
    public void testLazyInitialization() {
        LazyInitialization.Holder holder = LazyInitialization.getHolder();
        assertNotNull(holder);

        LazyInitialization.Holder sameHolder = LazyInitialization.getHolder();
        assertSame(holder, sameHolder);
    }
}
