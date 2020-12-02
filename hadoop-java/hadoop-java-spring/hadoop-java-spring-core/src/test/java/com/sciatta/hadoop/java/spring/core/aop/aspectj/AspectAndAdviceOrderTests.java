package com.sciatta.hadoop.java.spring.core.aop.aspectj;

import com.sciatta.hadoop.java.spring.core.aop.common.User;
import com.sciatta.hadoop.java.spring.core.aop.common.UserService;
import com.sciatta.hadoop.java.spring.core.aop.aspectj.config.DifferentAspectDifferentAdvice;
import com.sciatta.hadoop.java.spring.core.aop.aspectj.config.SameAspectDifferentAdvice;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yangxiaoyu on 2020/11/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderTests
 */
public class AspectAndAdviceOrderTests {
    @Test
    public void testSameAspectDifferentAdvice() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SameAspectDifferentAdvice.class);
        UserService userService = context.getBean("userService", UserService.class);
        assertNotNull(userService);
        
        User user = userService.findUser("root");
    }
    
    @Test(expected = RuntimeException.class)
    public void testSameAspectDifferentAdviceHasException() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SameAspectDifferentAdvice.class);
        UserService userService = context.getBean("userService", UserService.class);
        assertNotNull(userService);
    
        User user = userService.findUser("test");
    }
    
    @Test
    public void testDifferentAspectDifferentAdvice() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DifferentAspectDifferentAdvice.class);
        UserService userService = context.getBean("userService", UserService.class);
        assertNotNull(userService);
        
        User user = userService.findUser("root");
    }
    
    @Test(expected = RuntimeException.class)
    public void testDifferentAspectDifferentAdviceHasException() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DifferentAspectDifferentAdvice.class);
        UserService userService = context.getBean("userService", UserService.class);
        assertNotNull(userService);
        
        User user = userService.findUser("test");
    }
}
