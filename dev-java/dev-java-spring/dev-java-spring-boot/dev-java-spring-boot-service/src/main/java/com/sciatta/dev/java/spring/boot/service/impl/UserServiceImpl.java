package com.sciatta.dev.java.spring.boot.service.impl;

import com.sciatta.dev.java.spring.boot.service.UserService;
import com.sciatta.dev.java.spring.boot.service.pojo.User;
import com.sciatta.dev.java.spring.boot.service.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> findAllUsers() {
        return UserUtils.mockUsers(2);
    }
}
