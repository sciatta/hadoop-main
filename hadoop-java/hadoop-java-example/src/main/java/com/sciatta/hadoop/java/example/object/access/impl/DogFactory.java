package com.sciatta.hadoop.java.example.object.access.impl;

import com.sciatta.hadoop.java.example.object.access.common.AbstractAnimal;

/**
 * Created by yangxiaoyu on 2019/1/18<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * DogFactory
 */
public class DogFactory {
    private DogFactory() {
        // do nothing
    }

    public static AbstractAnimal create(String name, int age, String color) {
        Dog dog = new Dog();
        dog.setName(name);
        dog.setAge(age);
        dog.setColor(color);
        dog.trace();    // right trace是default，在同一个包中
        dog.run();  // right DogFactory和Dog在同一个包中，run是protected
        return dog;
    }
}
