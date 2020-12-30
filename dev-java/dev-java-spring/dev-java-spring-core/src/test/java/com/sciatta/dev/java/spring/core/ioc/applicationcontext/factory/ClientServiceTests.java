package com.sciatta.dev.java.spring.core.ioc.applicationcontext.factory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangxiaoyu on 2018/9/12<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * ClientServiceTests
 */
public class ClientServiceTests {
    private ApplicationContext applicationContext;

    @Before
    public void initContext() {
        applicationContext = new ClassPathXmlApplicationContext("application-context-factory.xml");
    }

    @Test
    public void testStaticFactory() {
        String say = "hello";
        ClientService clientService = applicationContext.getBean("clientServiceFromStaticFactory", ClientService.class);
        assertNotNull(clientService);
        assertEquals(say, clientService.echo(say));
    }

    @Test
    public void testInstanceFactory() {
        String say = "hello";
        ClientService clientService = applicationContext.getBean("clientServiceFromInstanceFactory", ClientService.class);
        assertNotNull(clientService);
        assertEquals(say, clientService.echo(say));
    }
}
