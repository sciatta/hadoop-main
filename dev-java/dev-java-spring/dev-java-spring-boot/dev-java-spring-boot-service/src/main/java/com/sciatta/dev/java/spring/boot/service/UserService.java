package com.sciatta.dev.java.spring.boot.service;

import com.sciatta.dev.java.spring.boot.service.pojo.User;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserService
 */
public interface UserService {
    List<User> findAllUsers();
}
