package com.sciatta.dev.java.spring.core.aop.jdk;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yangxiaoyu on 2020/11/30<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserProxyFactory
 */
public class UserProxyFactory {
    public static Object getUser(Object user) {
        // 1、运行时生成字节码 extends Proxy，implements Name，构造函数传入InvocationHandler实例
        // 2、ClassLoader运行时加载字节码
        // 3、代理对象实现了接口方法，调用方法时，会委托给InvocationHandler实例；注意InvocationHandler实例必须有target才可以回调目标方法
        return Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(), (proxy, method, args) -> {
            aroundPrint(method, "round in");
            Object o = method.invoke(user, args);
            aroundPrint(method, "round out");
            
            return o;
        });
    }
    
    private static void aroundPrint(Method method, String tip) {
        if (method.getName().startsWith("set")) {
            System.out.println(method.getName() + " " + tip);
        }
    }
}
