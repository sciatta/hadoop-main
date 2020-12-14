package com.sciatta.hadoop.java.spring.core.schema.definitionparser;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by yangxiaoyu on 2020/12/14<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SchoolNameSpaceHandler
 */
public class SchoolNameSpaceHandler extends NamespaceHandlerSupport {
    
    @Override
    public void init() {
        registerBeanDefinitionParser("school", new SchoolBeanDefinitionParser());
    }
}
