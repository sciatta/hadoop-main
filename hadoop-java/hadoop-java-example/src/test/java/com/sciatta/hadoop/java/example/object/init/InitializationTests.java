package com.sciatta.hadoop.java.example.object.init;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019/1/18<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * InitializationTests
 */
public class InitializationTests {
    @Test
    public void testInit() {
        String test = "hello";
        Initialization initialization = new Initialization();
        System.out.println("instance id = " + initialization.getId());
        System.out.println("class id = " + Initialization.getStaticId());

        // 1、初始化类；2、初始化实例
    }
}
