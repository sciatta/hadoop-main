package com.sciatta.dev.java.database.cache.service.impl;

import com.sciatta.dev.java.database.cache.dao.UserMapper;
import com.sciatta.dev.java.database.cache.model.User;
import com.sciatta.dev.java.database.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/2/13<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    
    @Override
    @Cacheable(value = "userCache", key = "#id")
    // value 要同ehcache.xml配置的cache name一致
    // key #参数名 或者 #p参数index
    public User find(int id) {
        return userMapper.find(id);
    }
    
    @Override
    @Cacheable(value = "userCache", key = "#root.methodName")
    public List<User> list() {
        return userMapper.list();
    }
}
