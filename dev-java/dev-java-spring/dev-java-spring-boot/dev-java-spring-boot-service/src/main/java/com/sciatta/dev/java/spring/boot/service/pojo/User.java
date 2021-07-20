package com.sciatta.dev.java.spring.boot.service.pojo;

import java.util.UUID;

/**
 * Created by yangxiaoyu on 2021/7/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * User
 */
public class User {
    private String id;
    private String userName;
    
    public User(String userName) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
    }
    
    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
