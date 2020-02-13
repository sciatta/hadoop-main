package com.sciatta.hadoop.hbase.example.user;

import com.sciatta.hadoop.hbase.example.user.impl.User;
import com.sciatta.hadoop.hbase.example.user.impl.UserService;

import java.io.IOException;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/2/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * GetUsers
 */
public class GetUsers {
    public static void main(String[] args) throws IOException {
        UserService userService = new UserService();

        userService.initUsers();

        List<User> users = userService.getUsers();

        for (User user : users) {
            System.out.println(user);
        }
    }
}
