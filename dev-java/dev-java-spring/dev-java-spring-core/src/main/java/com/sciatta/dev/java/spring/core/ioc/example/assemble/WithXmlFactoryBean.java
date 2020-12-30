package com.sciatta.dev.java.spring.core.ioc.example.assemble;

import com.sciatta.dev.java.spring.core.model.Name;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by yangxiaoyu on 2020/12/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * WithXmlFactoryBean
 */
public class WithXmlFactoryBean extends BaseBean {
    
    static class MyFactoryBean implements FactoryBean<Name> {
        
        @Override
        public Name getObject() throws Exception {
            return BaseBean.newUser();
        }
        
        @Override
        public Class<?> getObjectType() {
            return Name.class;
        }
        
        @Override
        public boolean isSingleton() {
            return true;
        }
    }
}
