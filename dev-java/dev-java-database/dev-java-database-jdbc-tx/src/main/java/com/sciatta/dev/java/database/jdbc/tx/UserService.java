package com.sciatta.dev.java.database.jdbc.tx;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/17<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserService
 */
public interface UserService {
    
    void insertUser(User user, boolean needRuntimeException);
    
    void insertUserTestPropagation(User user, boolean firstException, boolean secondException);
    
    List<User> findAllUsers();
}
