package com.sciatta.hadoop.hbase.example.user.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/2/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserService
 */
public class UserService {
    private UserDao userDao = UserDao.getUserDao();

    public void initUsers() throws IOException {
        // 删除user表
        userDao.deleteTable();

        // 创建user表
        userDao.createTable();

        // 增加数据
        userDao.addUser(createUsers());
    }

    public User getUser(String id) throws IOException {
        // 查询一行数据
        return userDao.getUserById(id);
    }

    public List<User> getUsers() throws IOException {
        // 查询全部数据
        return userDao.getAllUsers();
    }

    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        users.add(createUser("1", "rain", "20", "moon"));
        users.add(createUser("2", "yoyo", "19", "earth"));
        users.add(createUser("3", "lucky", "3", "zoo"));
        return users;
    }

    private User createUser(String id, String name, String age, String address) {
        User user = new User(id);
        user.setName(name);
        user.setAge(age);
        user.setAddress(address);
        return user;
    }
}
