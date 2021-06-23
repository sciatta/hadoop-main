package com.sciatta.dev.java.designpattern.creative.factory;

import com.sciatta.dev.java.designpattern.creative.factory.product.Animal;
import com.sciatta.dev.java.designpattern.creative.factory.product.Cat;
import com.sciatta.dev.java.designpattern.creative.factory.product.Dog;
import com.sun.applet2.AppletParameters;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FactoryMethod
 */
public class FactoryMethod {
    public static final String CAT = "cat";
    public static final String DOG = "dog";
    
    private static final HashMap<String, AnimalFactory> animalFactories = new LinkedHashMap<>();
    
    static abstract class AnimalFactory {
        abstract Animal create();
    }
    
    static class CatFactory extends AnimalFactory {
        
        @Override
        Animal create() {
            return new Cat();   // 把复杂的创建逻辑封装在不同的工厂中
        }
    }
    
    static class DocFactory extends AnimalFactory {
        
        @Override
        Animal create() {
            return new Dog();
        }
    }
    
    static {
        // 注册工厂
        animalFactories.put(FactoryMethod.CAT, new CatFactory());
        animalFactories.put(FactoryMethod.DOG, new DocFactory());
    }
    
    public static Animal create(String type) {
        AnimalFactory animalFactory = animalFactories.get(type);    // 假设是if，逻辑也不会很复杂
        
        if (animalFactory == null) {
            throw new IllegalArgumentException("type " + type + "not exists");
        }
        
        return animalFactory.create();
    }
}
