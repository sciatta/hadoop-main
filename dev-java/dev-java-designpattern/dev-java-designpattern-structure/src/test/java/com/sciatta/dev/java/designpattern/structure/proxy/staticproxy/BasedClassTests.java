package com.sciatta.dev.java.designpattern.structure.proxy.staticproxy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BasedClassTests
 */
public class BasedClassTests {
    @Test
    public void testDao() {
        BasedClass.User test = new BasedClass.UserDaoProxy().queryUserById("test");
        assertEquals("test", test.getId());
    }
}
