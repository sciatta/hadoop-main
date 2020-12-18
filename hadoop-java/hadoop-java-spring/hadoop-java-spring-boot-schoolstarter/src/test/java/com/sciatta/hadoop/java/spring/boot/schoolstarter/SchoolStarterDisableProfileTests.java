package com.sciatta.hadoop.java.spring.boot.schoolstarter;

import com.sciatta.hadoop.java.spring.boot.schoolstarter.config.SchoolAutoConfig;
import com.sciatta.hadoop.java.spring.boot.schoolstarter.model.School;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yangxiaoyu on 2020/12/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SchoolStarterProfileTests
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SchoolAutoConfig.class})
@ActiveProfiles("disable")
public class SchoolStarterDisableProfileTests {
    @Autowired
    private ApplicationContext context;
    
    @Test(expected = NoSuchBeanDefinitionException.class)
    public void testGetSchoolByContext() {
        School mySchool = (School) context.getBean("mySchool");
    }
}
