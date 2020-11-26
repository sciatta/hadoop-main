package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.scan.service;

import com.sciatta.hadoop.java.spring.core.ioc.model.User;

/**
 * Created by yangxiaoyu on 2018/9/12<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * UserDao
 */
public class UserDao {
    public User getUser(String name) {
        return createUser(name);
    }

    private User createUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }
}
