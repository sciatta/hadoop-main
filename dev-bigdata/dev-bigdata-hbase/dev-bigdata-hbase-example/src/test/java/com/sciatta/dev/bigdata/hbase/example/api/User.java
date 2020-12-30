package com.sciatta.dev.bigdata.hbase.example.api;

/**
 * Created by yangxiaoyu on 2020/2/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * User
 */
public class User {
    private String id;
    private String name;
    private String age;
    private String address;

    public User() {

    }

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
