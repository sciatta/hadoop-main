package com.sciatta.dev.java.database.jdbc.tx;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/17<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceTests
 */
public class UserServiceTests {
    private static UserService userService;
    
    @BeforeClass
    public static void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        userService = context.getBean("userService", UserService.class);
    }
    
    @Test
    public void testInsert() {
        userService.insertUser(newUser());
    }
    
    @Test
    public void testQuery() {
        List<User> allUsers = userService.findAllUsers();
        System.out.println(Arrays.toString(allUsers.toArray()));
    }
    
    private User newUser() {
        User user = new User();
        user.setName("bug");
        user.setNickname("BigBug");
        user.setPassword("important");
        user.setIdNumber("0987654321");
        return user;
    }
}
