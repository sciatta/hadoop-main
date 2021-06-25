package com.sciatta.dev.java.designpattern.structure.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ProductServiceProxy
 */
public class ProductServiceProxy {
    public static ProductService create(Class<?>[] interfaces) {
        return (ProductService) Proxy.newProxyInstance(ProductServiceProxy.class.getClassLoader(), interfaces,
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            System.out.println("in proxy");
                            
                            return rpc(args);   // 调用rpc方法
                        } finally {
                            System.out.println("out proxy");
                        }
                    }
                });
    }
    
    private static Product rpc(Object[] args) {
        System.out.println("rpc invoke " + Arrays.toString(args));
        return new Product((String) args[0]);
    }
    
    public static void main(String[] args) throws InterruptedException {
        ProductService productService = ProductServiceProxy.create(new Class[]{ProductService.class});
        System.out.println(productService.getClass().getName());
        
        while (true) {
            Thread.sleep(5000); // 查看动态生成代理类源码
        }
    }
}
