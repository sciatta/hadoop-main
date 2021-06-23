package com.sciatta.dev.java.designpattern.creative.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/22<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * HungaryTests
 */
public class HungaryTests {
    @Test
    public void testGetInstance() {
        Hungary i1 = Hungary.getInstance();
        assertNotNull(i1);
        
        Hungary i2 = Hungary.getInstance();
        assertNotNull(i2);
        
        assertTrue(i1 == i2);
    }
}
