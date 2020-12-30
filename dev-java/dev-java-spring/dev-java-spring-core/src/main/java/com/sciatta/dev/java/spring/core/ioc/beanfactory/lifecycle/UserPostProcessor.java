package com.sciatta.dev.java.spring.core.ioc.beanfactory.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by yangxiaoyu on 2018/11/10<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * UserPostProcessor
 */
public class UserPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserWithInit) {
            UserWithInit user = (UserWithInit) bean;
            System.out.println("before init: " + user.getName());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserWithInit) {
            UserWithInit user = (UserWithInit) bean;
            System.out.println("after init: " + user.getName());

        }
        return bean;
    }
}
