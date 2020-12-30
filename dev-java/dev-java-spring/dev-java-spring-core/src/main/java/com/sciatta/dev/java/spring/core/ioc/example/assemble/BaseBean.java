package com.sciatta.dev.java.spring.core.ioc.example.assemble;

import com.sciatta.dev.java.spring.core.model.Name;
import com.sciatta.dev.java.spring.core.model.User;

/**
 * Created by yangxiaoyu on 2020/12/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * BaseBean
 */
public abstract class BaseBean {
    protected Name user;
    
    public Name getUser() {
        return user;
    }
    
    public void setUser(Name user) {
        this.user = user;
    }
    
    public static Name newUser() {
        User user = new User();
        user.setName("rain");
        return user;
    }
}
