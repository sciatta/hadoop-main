package com.sciatta.hadoop.java.spring.core.schema;

import com.sciatta.hadoop.java.spring.core.model.Klass;
import com.sciatta.hadoop.java.spring.core.model.School;
import com.sciatta.hadoop.java.spring.core.model.Student;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by yangxiaoyu on 2020/12/14<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * DeanDefinitionTests
 */
public class SchemaTests {
    @Test
    public void testSchool() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("schema-definition-parser.xml");
        
        School school = context.getBean("mySchool", School.class);
        assertNotNull(school);
        assertEquals("magicSchool", school.getName());
        assertEquals(2, school.getKlasses().size());
        
        for (int i = 0; i < school.getKlasses().size(); i++) {
            Klass klass = school.getKlasses().get(i);
            System.out.println(klass.getName());
            List<Student> students = klass.getStudents();
            for (int j = 0; j < students.size(); j++) {
                Student student = students.get(j);
                System.out.println("name=" + student.getName() + " age=" + student.getAge());
            }
            
        }
    }
}
