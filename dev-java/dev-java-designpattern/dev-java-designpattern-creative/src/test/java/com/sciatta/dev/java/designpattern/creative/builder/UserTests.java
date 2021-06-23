package com.sciatta.dev.java.designpattern.creative.builder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserTests
 */
public class UserTests {
    @Test
    public void testUser() {
        User user = new User.Builder()
                .setId("user")
                .setName("root")
                .setPassword("root")
                .setAge(18)
                .build();
        
        assertNotNull(user);
        assertEquals("user", user.getId());
        assertEquals("root", user.getName());
        assertEquals("root", user.getPassword());
        assertEquals(18, user.getAge());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUserIdError() {
        User user = new User.Builder()
                .setName("root")
                .setPassword("root")
                .setAge(18)
                .build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUserAgeError() {
        User user = new User.Builder()
                .setId("user")
                .setName("root")
                .setPassword("root")
                .setAge(-10)
                .build();
    }
}
