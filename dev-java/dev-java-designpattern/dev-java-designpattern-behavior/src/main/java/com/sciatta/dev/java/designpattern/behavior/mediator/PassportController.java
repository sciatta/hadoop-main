package com.sciatta.dev.java.designpattern.behavior.mediator;

/**
 * Created by yangxiaoyu on 2021/7/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * PassportController
 */
public interface PassportController {
    
    void addUser(String userName, String password);
    
    Boolean canLogin(String userName, String password);
}
