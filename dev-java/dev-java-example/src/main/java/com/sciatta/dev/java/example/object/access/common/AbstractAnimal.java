package com.sciatta.dev.java.example.object.access.common;

/**
 * Created by yangxiaoyu on 2019/1/18<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * AbstractAnimal
 */
public abstract class AbstractAnimal {
    private String name;
    protected int age;
    String color;

    public void doSth() {
        run();
    }

    protected void run() {
        throw new RuntimeException("child class need implement");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
