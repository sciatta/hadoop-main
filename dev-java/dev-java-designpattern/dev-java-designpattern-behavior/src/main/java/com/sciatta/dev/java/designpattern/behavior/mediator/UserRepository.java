package com.sciatta.dev.java.designpattern.behavior.mediator;

/**
 * Created by yangxiaoyu on 2021/7/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserRepository
 */
public interface UserRepository {
    void addUser(User user);
    
    void deleteUserByName(String userName);
    
    void modifyUser(User user);
    
    User queryUserByUserName(String userName);
}
