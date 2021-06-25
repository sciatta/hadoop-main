package com.sciatta.dev.java.designpattern.structure.facade;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * User
 */
public class User {
    private String id;
    private String userName;
    
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
}
