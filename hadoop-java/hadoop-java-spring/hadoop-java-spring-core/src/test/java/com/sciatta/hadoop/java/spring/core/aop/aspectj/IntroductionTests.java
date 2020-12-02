package com.sciatta.hadoop.java.spring.core.aop.aspectj;

import com.sciatta.hadoop.java.spring.core.aop.common.User;
import com.sciatta.hadoop.java.spring.core.aop.common.UserService;
import com.sciatta.hadoop.java.spring.core.aop.aspectj.config.IntroductionConfig;
import com.sciatta.hadoop.java.spring.core.aop.aspectj.introduction.Print;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yangxiaoyu on 2020/11/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * IntroductionTests
 */
public class IntroductionTests {
    @Test
    public void testClassAddMethod() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IntroductionConfig.class);
        UserService service = context.getBean("userService", UserService.class);
        
        User user = service.findUser("root");
        assertNotNull(user);
        
        assertTrue(service instanceof Print);
        Print print = (Print) service;
        print.doPrint(user.getName());
    }
    
    @Test
    public void testWeavingAddMethod() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IntroductionConfig.class);
        UserService service = context.getBean("userService", UserService.class);
    
        User user = service.findUser("root");
        assertNotNull(user);
    }
}
