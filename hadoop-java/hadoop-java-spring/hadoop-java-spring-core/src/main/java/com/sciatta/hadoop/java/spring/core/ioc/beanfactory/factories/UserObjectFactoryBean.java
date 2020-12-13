package com.sciatta.hadoop.java.spring.core.ioc.beanfactory.factories;


import com.sciatta.hadoop.java.spring.core.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

/**
 * Created by yangxiaoyu on 2018/10/25<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * UserObjectFactoryBean
 */
public class UserObjectFactoryBean implements ObjectFactory<User> {
    @Override
    public User getObject() throws BeansException {
        User user = new User();
        user.setName("objectFactory");
        return user;
    }
}
