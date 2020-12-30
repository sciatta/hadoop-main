package com.sciatta.dev.java.spring.core.ioc.applicationcontext.scan.service;

import com.sciatta.dev.java.spring.core.model.User;

/**
 * Created by yangxiaoyu on 2018/9/11<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * UserServiceImpl
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public User getUser(String name) {
        return userDao.getUser(name);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
