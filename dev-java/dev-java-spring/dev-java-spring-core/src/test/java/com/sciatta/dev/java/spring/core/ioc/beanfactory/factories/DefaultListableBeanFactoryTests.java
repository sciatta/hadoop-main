package com.sciatta.dev.java.spring.core.ioc.beanfactory.factories;

import com.sciatta.dev.java.spring.core.model.User;
import com.sciatta.dev.java.spring.core.ioc.beanfactory.AbstractBeanFactoryTests;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * Created by yangxiaoyu on 2018/10/5<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * DefaultListableBeanFactoryTests
 */
public class DefaultListableBeanFactoryTests extends AbstractBeanFactoryTests {
    @Test
    public void testGetBeanAndAlias() {
        DefaultListableBeanFactory factory = getBeanFactory("bean-factory-alias.xml");

        User user = (User) factory.getBean("user");
        assertEquals("dog", user.getName());

        User cache = (User) factory.getBean("user");    // 默认singleton
        assertSame(cache, user);

        User user1 = (User) factory.getBean("user1");   // 别名
        assertSame(user1, user);
        assertSame(user1, cache);
    }

    @Test
    public void testPrototype() {
        DefaultListableBeanFactory factory = getBeanFactory("bean-factory-prototype.xml");

        User user = (User) factory.getBean("user");
        assertEquals("dog", user.getName());

        User prototype = (User) factory.getBean("user");
        assertNotSame(prototype, user);

        User user1 = (User) factory.getBean("user1");
        assertNotSame(user1, user);
        assertNotSame(user1, prototype);
    }
    
    @Test
    public void testRequiredType() {
        DefaultListableBeanFactory factory = getBeanFactory("bean-factory-requiredtype.xml");
        User user = factory.getBean("user", User.class);    // 不需要转型
        assertEquals("lucky", user.getName());
    }
    
    @Test
    public void testParentBeanFactory() {
        BeanFactory parent = getBeanFactory("bean-factory-parent.xml");
        BeanFactory child = getBeanFactory("bean-factory-child.xml", parent);

        User lucky = child.getBean("dog", User.class);  // parent不为null & 当前factory中不包含；即优先当前factory中查找
        assertEquals("lucky", lucky.getName());
    }

    @Test
    public void testDependsOn() {
        DefaultListableBeanFactory factory = getBeanFactory("bean-factory-dependson.xml");

        User lucky = factory.getBean("dog", User.class);    // 优先实例化依赖的Bean
        assertEquals("lucky", lucky.getName());
    }
}
