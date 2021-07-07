package com.sciatta.dev.java.designpattern.behavior.mediator;

/**
 * Created by yangxiaoyu on 2021/7/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserService
 */
public interface UserService {
    
    Boolean checkUserIsValid(String userName, String password);
    
    void addUser(User user);
}
