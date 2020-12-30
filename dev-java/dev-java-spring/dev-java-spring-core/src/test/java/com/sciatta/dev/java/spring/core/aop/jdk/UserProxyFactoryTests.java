package com.sciatta.dev.java.spring.core.aop.jdk;

import com.sciatta.dev.java.spring.core.model.Name;
import com.sciatta.dev.java.spring.core.model.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2020/11/30<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserProxyFactoryTests
 */
public class UserProxyFactoryTests {
    @Test
    public void testGetUser() {
        User user = new User();
        Object ret = UserProxyFactory.getUser(user);
        
        assertTrue(ret instanceof Name);
        
        String name = "root";
        Name retUser = (Name) ret;
        retUser.setName(name);
        
        assertEquals(name, retUser.getName());
    }
}
