package com.sciatta.hadoop.java.jvm.classload.initclass.impl;

import com.sciatta.hadoop.java.jvm.classload.initclass.Dog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ReflectMethodCallInit
 */
public class ReflectMethodCallInit {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Dog.class.getDeclaredMethod("eat");
        method.invoke(new Dog());
    }
}
