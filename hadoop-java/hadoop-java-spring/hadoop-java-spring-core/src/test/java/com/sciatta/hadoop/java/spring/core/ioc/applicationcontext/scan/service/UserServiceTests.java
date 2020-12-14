package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.scan.service;

import com.sciatta.hadoop.java.spring.core.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangxiaoyu on 2018/9/12<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * UserServiceTests
 */
public class UserServiceTests {
    private ApplicationContext applicationContext;

    @Before
    public void initContext() {
        applicationContext = new ClassPathXmlApplicationContext("application-context-service.xml");
    }

    @Test
    public void testUserService() {
        String name = "test";
        UserService userService = (UserService) applicationContext.getBean("userService");
        User user = userService.getUser(name);

        assertEquals(name, user.getName());
    }

    @Test
    public void testAlias() {
        // 通过bean的alias从容器中获取bean
        UserService userService;
        String name = "user1";
        userService = applicationContext.getBean("userService1", UserService.class);
        User user = userService.getUser(name);

        assertNotNull(user);
        assertEquals(name, user.getName());

        name = "user2";
        userService = applicationContext.getBean("userService2", UserService.class);
        user = userService.getUser(name);

        assertNotNull(user);
        assertEquals(name, user.getName());
    }
}
