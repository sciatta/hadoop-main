package com.sciatta.hadoop.java.spring.core.aop.aspectj.time;

import com.sciatta.hadoop.java.spring.core.aop.common.PrintUtils;
import com.sciatta.hadoop.java.spring.core.aop.common.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiaoyu on 2020/11/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * TimeAspect 织入时间
 */
@Aspect
@Component
@Order(1)
// Order控制Aspect执行的顺序，越小越优先执行
public class TimeAspect {
    @Around(value = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)", argNames = "pjp,userName")
    public Object doAround(ProceedingJoinPoint pjp, String userName) throws Throwable {
        PrintUtils.printUser("TimeAspect around advice in", PrintUtils.getCurrentTimeMillis(), userName, null);
        
        Object user = pjp.proceed(new Object[]{"#" + userName + "#"});
        
        PrintUtils.printUser("TimeAspect around advice out", PrintUtils.getCurrentTimeMillis(), userName, user);
        
        return user;
    }
    
    @Before(value = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)", argNames = "userName")
    public void doBefore(String userName) {
        PrintUtils.printUser("TimeAspect before advice", PrintUtils.getCurrentTimeMillis(), userName, null);
    }
    
    @After(value = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)", argNames = "userName")
    public void doAfter(String userName) {
        PrintUtils.printUser("TimeAspect after advice", PrintUtils.getCurrentTimeMillis(), userName, null);
    }
    
    @AfterReturning(pointcut = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)",
            returning = "retUser", argNames = "userName,retUser")
    public void doAfterReturning(String userName, User retUser) {
        PrintUtils.printUser("TimeAspect after returning advice", PrintUtils.getCurrentTimeMillis(), userName, retUser);
    }
    
    @AfterThrowing(pointcut = "com.sciatta.hadoop.java.spring.core.aop.aspectj.CommonAspect.findUser(userName)",
            throwing = "e", argNames = "userName,e")
    public void doAfterThrowing(String userName, RuntimeException e) {
        PrintUtils.printUser("TimeAspect after throwing advice", PrintUtils.getCurrentTimeMillis(), userName, e);
    }
}
