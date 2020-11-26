package com.sciatta.hadoop.java.spring.core.ioc.beanfactory.factories;

import com.sciatta.hadoop.java.spring.core.ioc.model.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by yangxiaoyu on 2018/10/24<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * UserPrototypeFactoryBean
 */
public class UserPrototypeFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setName("prototype");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
