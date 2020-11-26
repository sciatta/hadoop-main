package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.scan.service;

import com.sciatta.hadoop.java.spring.core.ioc.model.User;

/**
 * Created by yangxiaoyu on 2018/9/11<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * UserService
 */
public interface UserService {
    User getUser(String name);
}
