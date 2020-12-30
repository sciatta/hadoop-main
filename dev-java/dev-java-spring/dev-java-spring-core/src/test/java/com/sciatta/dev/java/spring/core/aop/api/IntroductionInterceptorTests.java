package com.sciatta.dev.java.spring.core.aop.api;

import com.sciatta.dev.java.spring.core.aop.api.introduction.Lockable;
import com.sciatta.dev.java.spring.core.model.Name;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangxiaoyu on 2020/11/29<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * IntroductionInterceptorTests
 */
public class IntroductionInterceptorTests {
    private ApplicationContext context;
    
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("aop-api-introduction.xml");
    }
    
    @Test(expected = RuntimeException.class)
    public void testUser() {
        String name = "root";
        Name user = context.getBean("userWithLock", Name.class);
        user.setName(name);
        
        if (user instanceof Lockable) {
            Lockable userWithLock = (Lockable) user;
            userWithLock.lock();
        }
        
        // 被锁，再次setName出现异常
        user.setName(name);
    }
    
    @Test(expected = RuntimeException.class)
    public void testConflict() {
        Lockable user = context.getBean("userWithLock", Lockable.class);
        user.lock();
        
        // 有同一个advisor->相同advice->有状态，所以不可共享
        Name conflict = context.getBean("userWithLock1", Name.class);
        assertNotSame(conflict, user);
        
        conflict.setName("root");
    }
}
