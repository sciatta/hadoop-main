package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.lifecycle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangxiaoyu on 2018/9/16<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * XmlBasedLifecycleTests
 */
public class XmlBasedLifecycleTests {
    ConfigurableApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("application-context-lifecycle.xml");
    }

    @After
    public void destroy() {
        applicationContext.registerShutdownHook();// before jvm shutdown，close context
    }

    @Test
    public void test() {
        // do nothing
        // application context 默认加载初始化所有 bean
    }
}
