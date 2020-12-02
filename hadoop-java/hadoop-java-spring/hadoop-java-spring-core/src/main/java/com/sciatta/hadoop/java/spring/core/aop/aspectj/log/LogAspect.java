package com.sciatta.hadoop.java.spring.core.aop.aspectj.log;

import com.sciatta.hadoop.java.spring.core.aop.common.PrintUtils;
import com.sciatta.hadoop.java.spring.core.aop.common.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiaoyu on 2020/11/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LogAspect 织入日志
 */
@Aspect
@Component
@Order(2)
// Order控制Aspect执行的顺序，越小越优先执行
public class LogAspect {
    @Around(value = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)", argNames = "pjp,userName")
    public Object doAround(ProceedingJoinPoint pjp, String userName) throws Throwable {
        PrintUtils.printUser("LogAspect around advice in", "...", userName, null);
        
        Object user = pjp.proceed(new Object[]{"#" + userName + "#"});
        
        PrintUtils.printUser("LogAspect around advice out", "...", userName, user);
        
        return user;
    }
    
    @Before(value = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)", argNames = "userName")
    public void doBefore(String userName) {
        PrintUtils.printUser("LogAspect before advice", "...", userName, null);
    }
    
    @After(value = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)", argNames = "userName")
    public void doAfter(String userName) {
        PrintUtils.printUser("LogAspect after advice", "...", userName, null);
    }
    
    @AfterReturning(pointcut = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)",
            returning = "retUser", argNames = "userName,retUser")
    public void doAfterReturning(String userName, User retUser) {
        PrintUtils.printUser("LogAspect after returning advice", "...", userName, retUser);
    }
    
    @AfterThrowing(pointcut = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)",
            throwing = "e", argNames = "userName,e")
    public void doAfterThrowing(String userName, RuntimeException e) {
        PrintUtils.printUser("LogAspect after throwing advice", "...", userName, e);
    }
}
