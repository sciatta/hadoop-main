package com.sciatta.hadoop.java.spring.core.aop.common;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by yangxiaoyu on 2020/11/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 定义公共Pointcut表达式
 */
public class CommonAspect {
    // args 运行时参数匹配
    @Pointcut("execution(* com.sciatta.hadoop.java.spring.core.aop.common.UserService.findUser(String)) && args(userName)") // pointcut表达式
    public void findUser(String userName) { // pointcut签名
        // pointcut签名 -> pointcut表达式
    }
}
