package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by yangxiaoyu on 2018/9/17<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * AnimalBeanPostProcessor<p/>
 * After instance constructor executed and before init method executed or after, you may modify instance returned
 */
public class AnimalBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Animal) {
            Animal animal = (Animal) bean;
            System.out.println("Before init, Bean:" + beanName + " Object:" + animal.toString());
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Animal) {
            Animal animal = (Animal) bean;
            System.out.println("After init, Bean:" + beanName + " Object:" + animal.toString());
        }
        return bean;
    }
}
