package com.sciatta.dev.java.database.datasource.service;

import com.sciatta.dev.java.database.datasource.model.User;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/1/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserService
 */
public interface UserService {
    int insertUser(User user);
    
    int deleteUser(int id);
    
    int updateUserName(User user);
    
    User getUserById(int id);
    
    List<User> getAllUsers();
}
