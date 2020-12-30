package com.sciatta.dev.java.spring.core.schema.definitionparser;

import com.sciatta.dev.java.spring.core.model.Klass;
import com.sciatta.dev.java.spring.core.model.School;
import com.sciatta.dev.java.spring.core.model.Student;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Created by yangxiaoyu on 2020/12/14<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SchoolBeanDefinitionParser
 */
public class SchoolBeanDefinitionParser extends AbstractBeanDefinitionParser {
    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        return parseSchool(element);
    }
    
    private AbstractBeanDefinition parseSchool(Element element) {
        // Class -> BeanDefinitionBuilder
        BeanDefinitionBuilder school = BeanDefinitionBuilder.rootBeanDefinition(School.class);
        
        // BeanDefinitionBuilder -> parse element -> BeanDefinitionBuilder
        school.addPropertyValue("name", element.getAttribute("name"));
        
        List<Element> klasses = DomUtils.getChildElementsByTagName(element, "klass");
        if (klasses != null && klasses.size() > 0) {
            parseKlasses(klasses, school);
        }
        
        // BeanDefinitionBuilder -> BeanDefinition
        return school.getBeanDefinition();
    }
    
    private void parseKlasses(List<Element> klassesElements, BeanDefinitionBuilder school) {
        ManagedList<BeanDefinition> klasses = new ManagedList<>(klassesElements.size());
        for (Element element : klassesElements) {
            klasses.add(parseKlass(element));
        }
        school.addPropertyValue("klasses", klasses);
    }
    
    private BeanDefinition parseKlass(Element element) {
        BeanDefinitionBuilder klass = BeanDefinitionBuilder.rootBeanDefinition(Klass.class);
        klass.addPropertyValue("name", element.getAttribute("name"));
        
        List<Element> students = DomUtils.getChildElementsByTagName(element, "student");
        if (students != null && students.size() > 0) {
            parseStudents(students, klass);
        }
        
        return klass.getBeanDefinition();
    }
    
    private void parseStudents(List<Element> studentsElements, BeanDefinitionBuilder klass) {
        ManagedList<Object> students = new ManagedList<>(studentsElements.size());
        for (Element element : studentsElements) {
            students.add(parseStudent(element));
        }
        klass.addPropertyValue("students", students);
    }
    
    private BeanDefinition parseStudent(Element element) {
        BeanDefinitionBuilder student = BeanDefinitionBuilder.rootBeanDefinition(Student.class);
        Element name = DomUtils.getChildElementByTagName(element, "name");  // 获取子元素
        if (name != null) {
            student.addPropertyValue("name", name.getTextContent());    // 获取元素内容
        }
        
        Element age = DomUtils.getChildElementByTagName(element, "age");
        if (age != null) {
            student.addPropertyValue("age", age.getTextContent());
        }
        
        return student.getBeanDefinition();
    }
}
