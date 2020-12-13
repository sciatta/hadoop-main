package com.sciatta.hadoop.java.spring.core.ioc.example.assemble;

import com.sciatta.hadoop.java.spring.core.model.Name;
import com.sciatta.hadoop.java.spring.core.model.User;

/**
 * Created by yangxiaoyu on 2020/12/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * WithXmlInstanceFactory
 */
public class WithXmlInstanceFactory extends BaseBean{
    
    static class InstanceFactory {
        public Name newUser() {
            return BaseBean.newUser();
        }
    }
}
