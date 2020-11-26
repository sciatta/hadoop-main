package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;

/**
 * Created by yangxiaoyu on 2018/9/17<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * AnimalBeanFactoryPostProcessor<p/>
 * After read config files and before instance scan, then modify metadata of scan definition.
 */
public class AnimalBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        
        System.out.println("execute scan factory post processor");
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("animal");
        MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
        
        if (mutablePropertyValues.contains("name")) {
            PropertyValue original = mutablePropertyValues.getPropertyValue("name");
            
            if (original.getValue() instanceof TypedStringValue) {
                TypedStringValue value = (TypedStringValue) original.getValue();
                // 覆盖已有属性
                mutablePropertyValues.addPropertyValue(new PropertyValue(original.getName(), "#" + value.getValue() + "#"));
            }
        }
    }
}
