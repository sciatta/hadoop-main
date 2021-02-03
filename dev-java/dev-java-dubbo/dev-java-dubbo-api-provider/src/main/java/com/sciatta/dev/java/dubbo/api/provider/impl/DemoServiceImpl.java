package com.sciatta.dev.java.dubbo.api.provider.impl;

import com.sciatta.dev.java.dubbo.api.Box;
import com.sciatta.dev.java.dubbo.api.DemoException;
import com.sciatta.dev.java.dubbo.api.DemoService;
import com.sciatta.dev.java.dubbo.api.User;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/2/2<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DemoServiceImpl
 */
public class DemoServiceImpl implements DemoService {
    private Box box;
    
    @Override
    public String sayName(String name) {
        return "say:" + name;
    }
    
    @Override
    public Box getBox() {
        return box;
    }
    
    @Override
    public void setBox(Box box) {
        this.box = box;
    }
    
    @Override
    public void throwDemoException() throws DemoException {
        throw new DemoException("DemoServiceImpl");
    }
    
    @Override
    public List<User> getUsers(List<User> users) {
        return users;
    }
    
    @Override
    public int echo(int i) {
        return i;
    }
}
