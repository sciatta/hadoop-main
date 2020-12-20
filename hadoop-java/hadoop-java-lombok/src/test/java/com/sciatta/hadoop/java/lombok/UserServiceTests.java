package com.sciatta.hadoop.java.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

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
}
