package com.sciatta.dev.java.database.jdbc.mybatis.dao;

import com.sciatta.dev.java.database.jdbc.mybatis.pojo.User;

/**
 * Created by yangxiaoyu on 2021/7/16<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserMapper
 */
public interface UserMapper {
    void insertUser(User user);
    User getUser(Integer id);
}
