package com.sciatta.hadoop.java.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

/**
 * Created by yangxiaoyu on 2020/12/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserServiceTests
 */
@Slf4j
public class UserServiceTests {
    @Test
    public void testUserService() {
        String name = "test";
        UserService userService = new UserService();
        
        User user = userService.findUser(name);
        log.debug("return user: " + user);
        
        assertEquals(name, user.getName());
    }
    
    @Test
    public void testMockito() {
        UserService mock = Mockito.mock(UserService.class, RETURNS_DEEP_STUBS);
        Mockito.when(mock.findUser("").getName()).thenReturn("mock");
        
        assertEquals("mock", mock.findUser("").getName());
    }
}
