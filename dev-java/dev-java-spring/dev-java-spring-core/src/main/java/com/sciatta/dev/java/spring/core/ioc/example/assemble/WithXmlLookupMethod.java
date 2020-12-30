package com.sciatta.dev.java.spring.core.ioc.example.assemble;

import com.sciatta.dev.java.spring.core.model.Name;

/**
 * Created by yangxiaoyu on 2020/12/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * WithXmlLookupMethod
 */
public class WithXmlLookupMethod extends BaseBean {
    static abstract class LookupFactory {
        public abstract Name newUser();
    }
}
