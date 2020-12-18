package com.sciatta.hadoop.java.spring.boot.schoolstarter;

import com.sciatta.hadoop.java.spring.boot.schoolstarter.config.SchoolAutoConfig;
import com.sciatta.hadoop.java.spring.boot.schoolstarter.model.School;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yangxiaoyu on 2020/12/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SchoolStarterTests
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SchoolAutoConfig.class})
public class SchoolStarterDefaultProfileTests {
    @Autowired
    private SchoolAutoConfig schoolAutoConfig;
    
    @Autowired
    private School school;
    
    @Autowired
    private ApplicationContext context;
    
    @Test
    public void testSchoolAutoConfig() {
        School school = schoolAutoConfig.school();
        assertNotNull(school);
        
        assertEquals("defaultSchool", school.getName());
        assertEquals(2, school.getKlasses().size());
    }
    
    @Test
    public void testSchool() {
        assertEquals("defaultSchool", school.getName());
        assertEquals(2, school.getKlasses().size());
    }
    
    @Test
    public void testGetSchoolByContext() {
        School mySchool = (School) context.getBean("mySchool");
        assertNotNull(mySchool);
        assertEquals("defaultSchool", school.getName());
        assertEquals(2, mySchool.getKlasses().size());
    }
}
