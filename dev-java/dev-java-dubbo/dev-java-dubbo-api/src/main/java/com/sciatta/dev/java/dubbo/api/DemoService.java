package com.sciatta.dev.java.dubbo.api;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/2/2<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DemoService
 */
public interface DemoService {
    
    String sayName(String name);
    
    Box getBox();   // get属性方法
    
    void setBox(Box box);   // set属性方法
    
    void throwDemoException() throws DemoException;
    
    List<User> getUsers(List<User> users);
    
    int echo(int i);
    
}
