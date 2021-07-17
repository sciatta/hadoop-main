package com.sciatta.dev.java.database.jdbc.tx;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/17<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserService
 */
public interface UserService {
    @Transactional(propagation = Propagation.REQUIRED)
    void insertUser(User user);
    List<User> findAllUsers();
}
