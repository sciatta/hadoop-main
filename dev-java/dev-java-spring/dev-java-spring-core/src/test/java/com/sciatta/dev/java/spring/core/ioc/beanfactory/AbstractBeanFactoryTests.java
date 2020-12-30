package com.sciatta.dev.java.spring.core.ioc.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by yangxiaoyu on 2020/11/22<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AbstractBeanFactory
 */
public abstract class AbstractBeanFactoryTests {
    protected DefaultListableBeanFactory getBeanFactory(String xml, BeanPostProcessor... processors) {
        return getBeanFactory(xml, null, processors);
    }
    
    protected DefaultListableBeanFactory getBeanFactory(String xml, BeanFactory parent, BeanPostProcessor... processors) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        // set parent factory
        if (parent != null) {
            factory.setParentBeanFactory(parent);
        }
        
        // add BeanPostProcessor
        if (processors != null && processors.length > 0) {
            for (BeanPostProcessor processor : processors) {
                factory.addBeanPostProcessor(processor);
            }
        }
        
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource(xml));
        
        return factory;
    }
}
