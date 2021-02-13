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
    
    @RequestMapping("/user/find")
    public User find(int id) {
        return userService.find(id);
    }
    
    @RequestMapping("/user/list")
    public List<User> list() {
        return userService.list();
    }
}
