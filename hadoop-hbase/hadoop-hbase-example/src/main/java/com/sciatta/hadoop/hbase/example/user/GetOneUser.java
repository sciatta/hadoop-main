package com.sciatta.hadoop.hbase.example.user;

import com.sciatta.hadoop.hbase.example.user.impl.User;
import com.sciatta.hadoop.hbase.example.user.impl.UserService;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/2/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * GetOneUser
 */
public class GetOneUser {
    public static void main(String[] args) throws IOException {
        UserService userService = new UserService();

        userService.initUsers();
        User user = userService.getUser("1");

        System.out.println(user);
    }
}
