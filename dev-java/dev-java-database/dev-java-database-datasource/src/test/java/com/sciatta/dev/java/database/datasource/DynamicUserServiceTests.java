package com.sciatta.dev.java.database.datasource;

import com.sciatta.dev.java.database.datasource.config.DataSourceConfig;
import com.sciatta.dev.java.database.datasource.model.User;
import com.sciatta.dev.java.database.datasource.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2021/1/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * SimpleUserServiceTests
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DataSourceConfig.class})
public class DynamicUserServiceTests {
    @Autowired
    @Qualifier("dynamic")
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
        user.setName("lucky");
        int i = userService.updateUserName(user);
        System.out.println(i);
    }
    
    @Test
    public void testSelect() {
        for (int i = 0; i < 100; i++) {
            User userById = userService.getUserById(2);
            if (i % 2 == 0) {
                assertEquals("s1", userById.getDesc());
            } else {
                assertEquals("s2", userById.getDesc());
            }
        }
    }
}
