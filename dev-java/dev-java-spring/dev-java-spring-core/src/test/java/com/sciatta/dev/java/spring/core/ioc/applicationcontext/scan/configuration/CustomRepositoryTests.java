package com.sciatta.dev.java.spring.core.ioc.applicationcontext.scan.configuration;

import com.sciatta.dev.java.spring.core.ioc.applicationcontext.scan.AbstractBean;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2018/9/21<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * CustomRepositoryTests
 */
public class CustomRepositoryTests extends AbstractBean {
    @Before
    public void init() {
        initBeans(CustomRepositoryConfig.class);
        printBeanNames();
    }

    @Test
    public void testCustomRepository() {
        CustomRepository customRepository1 = applicationContext.getBean("customRepository1", CustomRepository.class);
        assertNotNull(customRepository1);

        CustomRepository customRepository2 = applicationContext.getBean("customRepository2", CustomRepository.class);
        assertNotNull(customRepository2);

        assertSame(customRepository1, customRepository2);
    }
}
