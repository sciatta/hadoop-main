package com.sciatta.hadoop.java.spring.core.aop.api;

import com.sciatta.hadoop.java.spring.core.model.User;
import com.sciatta.hadoop.java.spring.core.model.UserService;
import org.junit.Before;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2020/11/29<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ProxyFactoryBeanTests
 */
public class ProxyFactoryBeanTests {
    private ApplicationContext context;
    
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("aop-api.xml");
    }
    
    @Test
    public void testAdvice() {
        String name = "root";
        UserService service = context.getBean("userService", UserService.class);
        User user = service.findUser(name);
        assertEquals(name, user.getName());
        
        // 不同的factory bean，共用相同的Advice
        service = context.getBean("userService1", UserService.class);
        user = service.findUser(name);
    }
    
    @Test(expected = RuntimeException.class)
    public void testAdviceWithRuntimeException() {
        String name = "test";
        UserService service = context.getBean("userService", UserService.class);
        service.findUser(name);
    }
}
