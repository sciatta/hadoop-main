package com.sciatta.dev.java.spring.core.ioc.example.assemble.autowired;

import com.sciatta.dev.java.spring.core.model.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiaoyu on 2020/12/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * WithAutowired
 */
@Component
public class WithAutowired {
    
    private Name user;
    
    @Autowired
    public WithAutowired(Name user) {
        this.user = user;
    }
    
    public Name getUser() {
        return user;
    }
}
