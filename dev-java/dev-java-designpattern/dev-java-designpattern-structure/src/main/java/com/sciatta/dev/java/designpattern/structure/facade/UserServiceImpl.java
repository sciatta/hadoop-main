package com.sciatta.dev.java.designpattern.structure.facade;

import java.util.UUID;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceImpl
 */
public class UserServiceImpl implements UserService {
    @Override
    public User register(String userName) {
        System.out.println(userName + " register success");
        
        return new User(UUID.randomUUID().toString(), userName);
    }
}
