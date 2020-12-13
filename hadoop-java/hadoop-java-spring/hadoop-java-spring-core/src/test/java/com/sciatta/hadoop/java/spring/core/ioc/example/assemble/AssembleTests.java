package com.sciatta.hadoop.java.spring.core.ioc.example.assemble;

import com.sciatta.hadoop.java.spring.core.ioc.example.assemble.autowired.UserConfiguration;
import com.sciatta.hadoop.java.spring.core.ioc.example.assemble.autowired.WithAutowired;
import com.sciatta.hadoop.java.spring.core.model.Name;
import com.sciatta.hadoop.java.spring.core.model.User;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangxiaoyu on 2020/12/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AssembleTests
 */
public class AssembleTests {
    @Test
    public void testWithXmlBeanSet() {
        ApplicationContext context = getXmlContext();
        WithXmlBeanSet bean = context.getBean("withXmlBeanSet", WithXmlBeanSet.class);
        test(bean.getUser());
    }
    
    @Test
    public void testWithXmlBeanCons() {
        ApplicationContext context = getXmlContext();
        WithXmlBeanCons bean = context.getBean("withXmlBeanCons", WithXmlBeanCons.class);
        test(bean.getUser());
    }
    
    @Test
    public void testWithXmlFactoryMethod() {
        ApplicationContext context = getXmlContext();
        WithXmlFactoryMethod bean = context.getBean("withXmlFactoryMethod", WithXmlFactoryMethod.class);
        test(bean.getUser());
    }
    
    @Test
    public void testWithXmlInstanceFactory() {
        ApplicationContext context = getXmlContext();
        WithXmlInstanceFactory bean = context.getBean("withXmlInstanceFactory", WithXmlInstanceFactory.class);
        test(bean.getUser());
    }
    
    @Test
    public void testWithXmlFactoryBean() {
        ApplicationContext context = getXmlContext();
        WithXmlFactoryBean bean = context.getBean("withXmlFactoryBean", WithXmlFactoryBean.class);
        test(bean.getUser());
    }
    
    @Test
    public void testWithAutowired() {
        ApplicationContext context = getAnnotationContext(UserConfiguration.class);
        WithAutowired bean = context.getBean("withAutowired", WithAutowired.class);
        test(bean.getUser());
    }
    
    @Test
    public void testWithXmlLookupMethod() {
        ApplicationContext context = getXmlContext();
        WithXmlLookupMethod bean = context.getBean("withXmlLookupMethod", WithXmlLookupMethod.class);
        test(bean.getUser());
    }
    
    @Test
    public void testWithXmlApplicationContextAware() {
        ApplicationContext context = getXmlContext();
        WithXmlApplicationContextAware bean = context.getBean("withXmlApplicationContextAware", WithXmlApplicationContextAware.class);
        test(bean.getUser());
    }
    
    private ClassPathXmlApplicationContext getXmlContext() {
        return new ClassPathXmlApplicationContext("ioc-assemble.xml");
    }
    
    private AnnotationConfigApplicationContext getAnnotationContext(Class<?> klass) {
        return new AnnotationConfigApplicationContext(klass);
    }
    
    private void test(Name user) {
        assertNotNull(user);
        assertEquals("rain", user.getName());
    }
}
