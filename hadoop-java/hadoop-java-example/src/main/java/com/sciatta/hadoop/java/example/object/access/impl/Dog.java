package com.sciatta.hadoop.java.example.object.access.impl;


import com.sciatta.hadoop.java.example.object.access.common.AbstractAnimal;

/**
 * Created by yangxiaoyu on 2019/1/18<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * Dog 包访问权限
 */
class Dog extends AbstractAnimal {
    void trace() {
        System.out.println(getName() + " wang wang wang...");
    }

    @Override
    protected void run() {
        System.out.println(getName() + " run run run...");
    }
}
