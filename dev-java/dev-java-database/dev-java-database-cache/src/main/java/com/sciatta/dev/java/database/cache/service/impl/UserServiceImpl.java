package com.sciatta.dev.java.database.cache.service.impl;

import com.sciatta.dev.java.database.cache.dao.UserMapper;
import com.sciatta.dev.java.database.cache.model.User;
import com.sciatta.dev.java.database.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    @Cacheable(value = "users", key = "#id")
    // ====ehcache====
    // value 要同ehcache.xml配置的cache name一致
    // key #参数名 或者 #p参数index
    
    // ====redis====
    // value 相当于命名空间
    // key users::1，可以自定义key的生成方式
    public User find(int id) {
        return userMapper.find(id);
    }
    
    @Override
    // @Cacheable(value = "users", /*key = "#root.methodName",*/ keyGenerator = "keyGenerator")
    public List<User> list() {
        return userMapper.list();
    }
    
    @Override
    @CacheEvict(value = "users", key = "#id")  // 注意key的生成方式必须同添加时的一致
    public boolean delete(int id) {
        return userMapper.delete(id);
    }
    
    @Override
    @CachePut(value = "users", key = "#user.id")
    public User update(User user) {
        if (userMapper.update(user)) {
            return user;    // 缓存的是返回值
        }
        return null;
    }
    
}
