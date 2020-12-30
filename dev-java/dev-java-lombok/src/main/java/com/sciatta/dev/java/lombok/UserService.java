package com.sciatta.dev.java.lombok;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by yangxiaoyu on 2020/12/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserService
 */
@Slf4j // log4j
public class UserService {
    public User findUser(String userName) {
        log.debug("user name: " + userName);
        return new User(userName);
    }
}
