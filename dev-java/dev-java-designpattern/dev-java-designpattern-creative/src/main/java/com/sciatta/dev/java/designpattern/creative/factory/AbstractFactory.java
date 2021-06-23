package com.sciatta.dev.java.designpattern.creative.factory;

import com.sciatta.dev.java.designpattern.creative.factory.product.Animal;
import com.sciatta.dev.java.designpattern.creative.factory.product.Cat;
import com.sciatta.dev.java.designpattern.creative.factory.product.Dog;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AbstractFactory
 */
public class AbstractFactory {
    public static final String CAT = "cat";
    public static final String DOG = "dog";
    
    public static final String SUPER_ZOO = "superZoo";
    public static HashMap<String, Zoo> zoo = new LinkedHashMap<>();
    
    abstract static class Zoo { // 一个工厂可以创建多个不同类型的产品
        abstract Animal createCat();
        
        abstract Animal createDog();
    }
    
    static class SuperZoo extends Zoo { // 减少工厂数量
        
        @Override
        Animal createCat() {
            return new Cat();
        }
        
        @Override
        Animal createDog() {
            return new Dog();
        }
    }
    
    static {
        zoo.put(SUPER_ZOO, new SuperZoo());
    }
    
    public static Animal create(String factoryType, String productType) {
        Zoo zoo = AbstractFactory.zoo.get(factoryType);
        if (zoo == null) throw new IllegalArgumentException("factoryType " + factoryType + "not exists");
        
        if (CAT.equals(productType)) {
            return zoo.createCat();
        } else if (DOG.equals(productType)) {
            return zoo.createDog();
        } else {
            throw new IllegalArgumentException("productType " + productType + "not exists");
        }
    }
}
