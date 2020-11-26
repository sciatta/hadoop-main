package com.sciatta.hadoop.java.spring.core.ioc.beanfactory.factories;

import com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.factory.ClientService;
import com.sciatta.hadoop.java.spring.core.ioc.beanfactory.AbstractBeanFactoryTests;
import com.sciatta.hadoop.java.spring.core.ioc.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotSame;

/**
 * Created by yangxiaoyu on 2020/11/22<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FactoryTests
 */
public class FactoryTests extends AbstractBeanFactoryTests {
    private DefaultListableBeanFactory factory;
    
    @Before
    public void init() {
        factory = getBeanFactory("bean-factory-factories.xml");
    }
    
    @Test
    public void testFactoryBean() {
        // FactoryBean => singleton
        User singleton = (User) factory.getBean("singleton");
        assertEquals("singleton", singleton.getName());
        User cache = (User) factory.getBean("singleton");
        assertSame(cache, singleton);
    
        // FactoryBean => prototype
        User prototype = (User) factory.getBean("prototype");
        assertEquals("prototype", prototype.getName());
        User other = (User) factory.getBean("prototype");
        assertNotSame(prototype, other);
        
        // & 返回工厂本身
        Object beanFactory = factory.getBean("&singleton");
        assertTrue(beanFactory instanceof UserSingletonFactoryBean);
    }
    
    @Test
    public void testObjectFactory() {
        // 容器只维护ObjectFactory的实例
        ObjectFactory<User> of = (ObjectFactory<User>) factory.getBean("objectFactory");
        assertTrue(of instanceof UserObjectFactoryBean);
        User user = of.getObject();
        assertEquals("objectFactory", user.getName());
    
        User prototypeUser = of.getObject();
        assertNotSame(user, prototypeUser);  // 需要手动调用getObject，每次返回的是prototype类型的不同实例
    
        ObjectFactory<User> ofOther = (ObjectFactory) factory.getBean("objectFactory");
        assertTrue(ofOther instanceof UserObjectFactoryBean);
        User userOther = ofOther.getObject();
        assertEquals("objectFactory", userOther.getName());
    
        assertSame(of, ofOther);    // objectFactory在容器中默认是单例的
        assertNotSame(user, userOther); // objectFactory返回的实例，容器不负责维护
    }
}
