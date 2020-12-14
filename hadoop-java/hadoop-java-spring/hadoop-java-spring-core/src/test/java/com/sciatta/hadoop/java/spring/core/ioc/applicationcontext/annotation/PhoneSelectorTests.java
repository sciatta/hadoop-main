package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.annotation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by yangxiaoyu on 2018/9/19<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * PhoneSelectorTests
 */
public class PhoneSelectorTests {
    // ApplicationContext applicationContext;
    BeanFactory factory;
    PhoneSelector phoneSelector;
    
    private static final String BEAN_FACTORY = "beanFactory";
    private static final String APPLICATION_CONTEXT = "applicationContext";
    
    @Before
    public void init() {
        // buildFactory(BEAN_FACTORY);
        buildFactory(APPLICATION_CONTEXT);
        phoneSelector = factory.getBean("phoneSelector", PhoneSelector.class);
    }
    
    private void buildFactory(String factoryName) {
        if (BEAN_FACTORY.equals(factoryName)) {
            // 没有 <context:annotation-config/> 可以正常autowired，但是qualifier元素不起作用
            factory = new DefaultListableBeanFactory();
            
            AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
            autowiredAnnotationBeanPostProcessor.setBeanFactory(factory);
            ((DefaultListableBeanFactory) factory).addBeanPostProcessor(autowiredAnnotationBeanPostProcessor);
            
            CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor = new CommonAnnotationBeanPostProcessor();
            commonAnnotationBeanPostProcessor.setBeanFactory(factory);
            ((DefaultListableBeanFactory) factory).addBeanPostProcessor(commonAnnotationBeanPostProcessor);
            
            XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
            reader.loadBeanDefinitions(new ClassPathResource("application-context-annotation.xml"));
            
        } else if (APPLICATION_CONTEXT.equals(factoryName)) {
            factory = new ClassPathXmlApplicationContext("application-context-annotation.xml");
        }
    }
    
    @Test
    public void testPrimary() {
        Phone phone = phoneSelector.getPrimaryPhone();
        assertTrue(phone instanceof Iphone);
        assertEquals("iphone6plus", phone.getName());
    }
    
    @Test
    public void testQualifier() {
        Phone phone = phoneSelector.getQualifierPhone();
        assertTrue(phone instanceof Android);
        assertEquals("xiaomi1", phone.getName());
    }
    
    @Test
    public void testPhoneQualifier() {
        Phone phone = phoneSelector.getPhoneQualifier();
        assertTrue(phone instanceof Iphone);
        assertEquals("iphonex", phone.getName());
    }
    
    @Test
    public void testResource() {
        Phone phone = phoneSelector.getPhoneResource();
        assertTrue(phone instanceof Android);
        assertEquals("huawei", phone.getName());
        assertEquals(Phone.Color.RED, phone.getColor());
    }
}
