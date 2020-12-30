package com.sciatta.dev.java.spring.core.ioc.beanfactory.autowire;

import com.sciatta.dev.java.spring.core.ioc.beanfactory.AbstractBeanFactoryTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import static org.junit.Assert.assertNotNull;

/**
 * Created by yangxiaoyu on 2020/11/22<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OwnerTests
 */
public class OwnerTests extends AbstractBeanFactoryTests {
    private DefaultListableBeanFactory factory;
    
    @Before
    public void init() {
        factory = getBeanFactory("bean-factory-autowire.xml");
    }
    
    @Test
    public void testConstructor() {
        OwnerCons cons = (OwnerCons) factory.getBean("cons");   // 构造函数通过类型byType自动织入
        assertNotNull(cons.getUser());
    }
    
    @Test
    public void testByName() {
        OwnerPropByName prop = (OwnerPropByName) factory.getBean("propByName");   // 属性通过名称（Id）自动织入byName，且需要有setId
        assertNotNull(prop.getDog());
    }
    
    @Test
    public void testByType() {
        OwnerPropByType propByType = (OwnerPropByType) factory.getBean("propByType");   // 属性通过类型自动织入byType，且需要有setId
        assertNotNull(propByType.getDoggy());
    }
}
