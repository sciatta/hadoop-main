package com.sciatta.dev.java.designpattern.behavior.mediator.impl;

import com.sciatta.dev.java.designpattern.behavior.mediator.Mediator;
import com.sciatta.dev.java.designpattern.behavior.mediator.User;
import com.sciatta.dev.java.designpattern.behavior.mediator.UserService;

/**
 * Created by yangxiaoyu on 2021/7/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceImpl
 */
public class UserServiceImpl implements UserService {
    private final Mediator mediator;
    
    public UserServiceImpl(Mediator mediator) {
        this.mediator = mediator;
    }
    
    @Override
    public Boolean checkUserIsValid(String userName, String password) {
        User user = (User) mediator.handleService(ServiceMediator.ServiceName.UserRepository,
                "queryUserByUserName", new Class[]{String.class}, new Object[]{userName});
        
        if (user == null) {
            System.out.println("UserService checkUserIsValid user=" + user);
            return false;
        }
        return user.getUserName().equals(password);
    }
    
    @Override
    public void addUser(User user) {
        System.out.println("UserService invoked user=" + user);
        mediator.handleService(ServiceMediator.ServiceName.UserRepository, "addUser", new Class[]{User.class}, new Object[]{user});
    }
}
