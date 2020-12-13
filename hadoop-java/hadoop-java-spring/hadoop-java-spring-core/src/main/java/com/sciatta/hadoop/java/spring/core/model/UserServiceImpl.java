package com.sciatta.hadoop.java.spring.core.model;

import com.sciatta.hadoop.java.spring.core.aop.common.PrintUtils;
import org.springframework.stereotype.Service;

/**
 * Created by yangxiaoyu on 2020/11/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserServiceImpl
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public User findUser(String userName) {
        User user = null;
        if ("#root#".equals(userName) || "##root##".equals(userName) || "root".equals(userName)) {
            user = new User();
            user.setName(userName);
            PrintUtils.printUser("Method execute", "", userName, user);
            return user;
        } else {
            PrintUtils.printUser("Exception execute", "", userName, user);
            throw new RuntimeException("Exception execute");
        }
    }
}
