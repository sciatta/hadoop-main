package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.annotation;

/**
 * Created by yangxiaoyu on 2018/9/18<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Iphone
 */
public class Iphone implements Phone {
    private String name;
    private Color color;

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = Color.valueOf(color);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
