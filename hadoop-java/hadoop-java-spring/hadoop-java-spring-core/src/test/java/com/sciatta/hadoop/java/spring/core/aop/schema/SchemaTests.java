package com.sciatta.hadoop.java.spring.core.aop.schema;

import com.sciatta.hadoop.java.spring.core.aop.common.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangxiaoyu on 2020/11/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SchemaTests
 */
public class SchemaTests {
    private ClassPathXmlApplicationContext context;
    
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("aop-example.xml");
    }
    
    @Test
    public void testRetryOnce() {
        UserService service = context.getBean("userService", UserService.class);
        assertNotNull(service);
        
        service.findUser("root");
    }
    
    @Test(expected = Throwable.class)
    public void testRetryMoreAsException() {
        UserService service = context.getBean("userService", UserService.class);
        assertNotNull(service);
        
        service.findUser("test");
    }
}
