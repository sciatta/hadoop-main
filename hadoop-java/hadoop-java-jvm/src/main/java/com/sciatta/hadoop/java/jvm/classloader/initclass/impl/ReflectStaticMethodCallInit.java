package com.sciatta.hadoop.java.jvm.classloader.initclass.impl;

import com.sciatta.hadoop.java.jvm.classloader.initclass.Dog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 使用反射API对某个类进行反射调用时，初始化这个类，反射调用要么是已经有实例了，要么是静态方法，都需要初始化
 */
public class ReflectStaticMethodCallInit {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method run = Dog.class.getDeclaredMethod("run");
        run.invoke(Dog.class);
    }
}
