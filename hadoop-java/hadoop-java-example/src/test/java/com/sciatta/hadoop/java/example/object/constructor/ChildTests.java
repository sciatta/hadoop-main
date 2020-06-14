package com.sciatta.hadoop.java.example.object.constructor;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019/1/16<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ChildTests
 */
public class ChildTests {
    @Test
    public void testConstructor() {
        Child child = new Child();
    }

    @Test
    public void testDefaultConstructor() {
        NewChild child = new NewChild(1);
    }
}
