package com.sciatta.dev.java.database.datasource;

import com.sciatta.dev.java.database.datasource.config.DataSourceConfig;
import com.sciatta.dev.java.database.datasource.model.User;
import com.sciatta.dev.java.database.datasource.service.UserService;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yangxiaoyu on 2021/1/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceTests
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DataSourceConfig.class})
public class UserServiceTests {
    @Autowired
    private UserService userService;
    
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("rain");
        user.setDesc("m");
        int i = userService.insertUser(user);
        System.out.println(i);
    }
    
    @Test
    public void testDelete() {
        int i = userService.deleteUser(1);
        System.out.println(i);
    }
    
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(2);
        user.setName("yoyo");
        int i = userService.updateUserName(user);
        System.out.println(i);
    }
    
    @Test
    public void testSelect() {
        User userById = userService.getUserById(2);
        assertEquals("s",userById.getDesc());
    }
}
