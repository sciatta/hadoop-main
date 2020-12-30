package com.sciatta.dev.java.jvm.classloader.initclass.impl;

import com.sciatta.dev.java.jvm.classloader.initclass.Dog;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 当初次调用MethodHandle实例时，初始化该MethodHandle指向的方法所在的类
 */
public class MethodHandleInit {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        // 返回类型，参数类型（可省）
        MethodType methodType = MethodType.methodType(void.class);
        MethodHandle methodHandle = MethodHandles.lookup().findStatic(Dog.class, "run", methodType);

        try {
            methodHandle.invoke();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
