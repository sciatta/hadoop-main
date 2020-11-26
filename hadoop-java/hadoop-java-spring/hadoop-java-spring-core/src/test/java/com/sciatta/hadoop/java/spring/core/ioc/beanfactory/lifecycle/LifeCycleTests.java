package com.sciatta.hadoop.java.spring.core.ioc.beanfactory.lifecycle;

import com.sciatta.hadoop.java.spring.core.ioc.beanfactory.AbstractBeanFactoryTests;
import com.sciatta.hadoop.java.spring.core.ioc.model.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2020/11/22<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LifeCycleTests
 */
public class LifeCycleTests extends AbstractBeanFactoryTests {
    @Test
    public void testLifeCycle() {
        // BeanPostProcessor在BeanFactory中需要显式注册
        DefaultListableBeanFactory factory = getBeanFactory("bean-factory-lifecycle.xml", new UserPostProcessor());
        User user = factory.getBean("user", User.class);
        assertEquals("lucky", user.getName());
    }
}
