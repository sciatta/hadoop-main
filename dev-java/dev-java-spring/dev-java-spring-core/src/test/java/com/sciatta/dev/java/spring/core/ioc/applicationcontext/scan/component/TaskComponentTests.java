package com.sciatta.dev.java.spring.core.ioc.applicationcontext.scan.component;

import com.sciatta.dev.java.spring.core.ioc.applicationcontext.scan.AbstractBean;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2018/9/20<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * TaskComponentTests
 */
public class TaskComponentTests extends AbstractBean {

    @Before
    public void init() {
        initBeans(ServiceComponentConfig.class);

        printBeanNames();
    }

    @Test
    public void testCustomService() {
        CustomService customService1 = applicationContext.getBean("customService1", CustomService.class);
        assertNotNull(customService1);

        CustomService customService2 = applicationContext.getBean("customService2", CustomService.class);
        assertNotNull(customService2);

        assertNotSame(customService1, customService2);
    }

    @Test
    public void testPrototypeCustomService() {
        CustomService prototypeService1 = applicationContext.getBean("prototypeService", CustomService.class);
        assertNotNull(prototypeService1);

        CustomService prototypeService2 = applicationContext.getBean("prototypeService", CustomService.class);
        assertNotNull(prototypeService2);

        assertNotSame(prototypeService1, prototypeService2);
    }

    @Test
    public void testSingletonCustomService() {
        CustomService singletonService1 = applicationContext.getBean("singletonService", CustomService.class);
        assertNotNull(singletonService1);

        CustomService singletonService2 = applicationContext.getBean("singletonService", CustomService.class);
        assertNotNull(singletonService2);

        assertSame(singletonService1, singletonService2);
    }
}
