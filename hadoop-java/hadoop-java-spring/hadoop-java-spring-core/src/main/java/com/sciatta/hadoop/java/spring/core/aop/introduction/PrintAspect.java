package com.sciatta.hadoop.java.spring.core.aop.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiaoyu on 2020/11/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * PrintAspect
 */
@Aspect
@Component
public class PrintAspect {
    // 增强类功能，即原类拥有接口定义的功能
    @DeclareParents(value = "com.sciatta.hadoop.java.spring.core.aop.common.*Service+",
            defaultImpl = PrintImpl.class)
    private Print mixin;
    
    // pointcut表达式，this表示代理类是接口Print的实例，注入代理类
    @Before(value = "com.sciatta.hadoop.java.spring.core.aop.common.CommonAspect.findUser(userName) && this(print)",
            argNames = "userName,print")
    public void doPrint(String userName, Print print) {
        print.doPrint(userName);
    }
}
