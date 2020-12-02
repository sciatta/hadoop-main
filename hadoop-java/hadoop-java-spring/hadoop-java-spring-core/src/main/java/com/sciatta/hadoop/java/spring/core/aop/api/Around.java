package com.sciatta.hadoop.java.spring.core.aop.api;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by yangxiaoyu on 2020/11/29<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Around
 */
public class Around implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 最早执行
        System.out.println("Around in " + invocation);
        Object ret = invocation.proceed();  // 推动过滤器链执行，而不是推动下一个Advice执行，所以无需传递参数
        // 最晚执行
        System.out.println("Around out " + ret);
        // 返回给调用方，可以改变返回结果
        return ret;
    }
}
