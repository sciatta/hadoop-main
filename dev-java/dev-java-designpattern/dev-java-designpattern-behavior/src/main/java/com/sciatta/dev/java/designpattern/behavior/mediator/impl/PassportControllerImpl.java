package com.sciatta.dev.java.designpattern.behavior.mediator.impl;

import com.sciatta.dev.java.designpattern.behavior.mediator.Mediator;
import com.sciatta.dev.java.designpattern.behavior.mediator.PassportController;
import com.sciatta.dev.java.designpattern.behavior.mediator.User;

/**
 * Created by yangxiaoyu on 2021/7/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * PassportControllerImpl
 */
public class PassportControllerImpl implements PassportController {
    private final Mediator mediator;
    
    public PassportControllerImpl(Mediator mediator) {
        this.mediator = mediator;
    }
    
    @Override
    public void addUser(String userName, String password) {
        System.out.println("PassportController invoked userName=" + userName + " password=" + password);
        User user = new User(userName, password);
        mediator.handleService(ServiceMediator.ServiceName.UserService, "addUser", new Class[]{User.class}, new Object[]{user});
    }
    
    @Override
    public Boolean canLogin(String userName, String password) {
        return (Boolean) mediator.handleService(ServiceMediator.ServiceName.UserService,
                "checkUserIsValid", new Class[]{String.class, String.class}, new Object[]{userName, password});
    }
}
