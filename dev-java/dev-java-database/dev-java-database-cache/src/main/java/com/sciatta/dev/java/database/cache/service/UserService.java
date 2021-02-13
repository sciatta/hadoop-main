package com.sciatta.dev.java.database.cache.service;

import com.sciatta.dev.java.database.cache.model.User;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/2/13<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserService
 */
public interface UserService {
    User find(int id);
    
    List<User> list();
}
