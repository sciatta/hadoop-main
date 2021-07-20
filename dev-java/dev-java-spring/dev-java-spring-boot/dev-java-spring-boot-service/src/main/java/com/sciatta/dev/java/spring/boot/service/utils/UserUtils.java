package com.sciatta.dev.java.spring.boot.service.utils;

import com.sciatta.dev.java.spring.boot.service.pojo.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserUtil
 */
public class UserUtils {
    public static List<User> mockUsers(Integer userNumber) {
        User[] users = new User[userNumber];
        for (int i = 0; i < userNumber; i++) {
            users[i] = new User(RandomUtils.randomString(5));
        }
        
        return Arrays.asList(users);
    }
}
