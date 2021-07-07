package com.sciatta.dev.java.designpattern.behavior.mediator.impl;

import com.sciatta.dev.java.designpattern.behavior.mediator.Mediator;
import com.sciatta.dev.java.designpattern.behavior.mediator.User;
import com.sciatta.dev.java.designpattern.behavior.mediator.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/7/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserRepository
 */
public class UserRepositoryImpl implements UserRepository {
    private final Mediator mediator;
    private static Map<String, User> userMap = new HashMap<>();
    
    public UserRepositoryImpl(Mediator mediator) {
        this.mediator = mediator;
    }
    
    @Override
    public void addUser(User user) {
        userMap.put(user.getUserName(), user);
        System.out.println("UserRepository增加成功");
    }
    
    @Override
    public void deleteUserByName(String userName) {
        userMap.remove(userName);
        System.out.println("UserRepository删除成功");
    }
    
    @Override
    public void modifyUser(User user) {
        userMap.put(user.getUserName(), user);
        System.out.println("UserRepository修改成功");
    }
    
    @Override
    public User queryUserByUserName(String userName) {
        User user = userMap.get(userName);
        System.out.println("UserRepository查询成功,user=" + user);
        return user;
    }
}
