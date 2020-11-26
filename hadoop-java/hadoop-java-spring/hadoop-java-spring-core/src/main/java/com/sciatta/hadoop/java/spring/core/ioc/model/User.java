package com.sciatta.hadoop.java.spring.core.ioc.model;

/**
 * Created by yangxiaoyu on 2018/9/11<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * User
 */
public class User {
    private long id;
    private String name;
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void init() {
        System.out.println(toString() + " init");
    }
}
