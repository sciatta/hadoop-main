package com.sciatta.dev.java.designpattern.creative.builder;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * User
 */
public class User {
    private final String id;
    private final String name;
    private final String password;
    private final int age;
    
    // 只读
    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.password = builder.password;
        this.age = builder.age;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public int getAge() {
        return age;
    }
    
    public static class Builder {
        private String id;
        private String name;
        private String password;
        private int age;
        
        public User build() {
            // 对参数校验
            if (id == null || id.trim().equals("")) {
                throw new IllegalArgumentException("id must have a value");
            }
            
            if (!"root".equals(name)) {
                throw new IllegalArgumentException("name " + name + "is invalid");
            }
            
            if (!"root".equals(password)) {
                throw new IllegalArgumentException("password is invalid");
            }
            
            if (age <= 0 || age >= 150) {
                throw new IllegalArgumentException("age " + age + " is invalid");
            }
            
            return new User(this);
        }
        
        public Builder setId(String id) {
            this.id = id;
            return this;
        }
        
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
    }
}
