package com.sciatta.hadoop.java.spring.core.ioc.example.assemble;

import com.sciatta.hadoop.java.spring.core.model.Name;

import java.beans.beancontext.BeanContext;

/**
 * Created by yangxiaoyu on 2020/12/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * WithXmlFactoryMethod
 */
public class WithXmlFactoryMethod extends BaseBean {
    
    static class FactoryMethod {
        public static Name newUser() {
            return BaseBean.newUser();
        }
    }
}
