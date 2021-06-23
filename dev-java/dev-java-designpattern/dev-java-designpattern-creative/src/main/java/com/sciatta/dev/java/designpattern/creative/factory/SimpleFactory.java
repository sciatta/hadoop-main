package com.sciatta.dev.java.designpattern.creative.factory;

import com.sciatta.dev.java.designpattern.creative.factory.product.Animal;
import com.sciatta.dev.java.designpattern.creative.factory.product.Cat;
import com.sciatta.dev.java.designpattern.creative.factory.product.Dog;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * SimpleFactory
 */
public class SimpleFactory {
    public static final String CAT = "cat";
    public static final String DOG = "dog";
    
    public static Animal create(String type) {
        if (CAT.equals(type)) {
            return new Cat();   // 创建逻辑和if耦合在一起，增加产品，代码会变得越来越庞大，不易读
        } else if (DOG.equals(type)) {
            return new Dog();
        } else {
            throw new IllegalArgumentException("type " + type + "not exists");
        }
    }
}
