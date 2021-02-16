package com.sciatta.dev.java.database.cache.controller;

import com.sciatta.dev.java.database.cache.model.User;
import com.sciatta.dev.java.database.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/2/13<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserController
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    
    // http://localhost:8888/user/find\?id\=1
    @RequestMapping("/user/find")
    public User find(int id) {
        return userService.find(id);
    }
    
    // http://localhost:8888/user/list
    @RequestMapping("/user/list")
    public List<User> list() {
        return userService.list();
    }
    
    // http://localhost:8888/user/find\?id\=1
    @RequestMapping("/user/delete")
    public boolean delete(int id) {
        return userService.delete(id);
    }
    
    // http://localhost:8888/user/update\?id\=1\&name\=haha
    @RequestMapping("/user/update")
    public User update(User user) {
        return userService.update(user);
    }
}
