package com.sciatta.dev.java.spring.boot.api.user;

import com.sciatta.dev.java.spring.boot.service.UserService;
import com.sciatta.dev.java.spring.boot.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserController
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("test")
    public String test() {
        return "test!";
    }
    
    @GetMapping("findAllUsers")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }
    
}
