package com.sciatta.dev.java.database.datasource.lookup;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2021/1/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DynamicAspect
 */
@Aspect
@Component
public class DynamicAspect {
    @Pointcut("@annotation(CurrentDataSource)")
    public void lookupDataSource() {
    
    }
    
    @Around("lookupDataSource()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        
        beforeDoAround(pjp);
        
        Object ret = pjp.proceed(pjp.getArgs());
        
        afterDoAround();
        
        return ret;
    }
    
    public void beforeDoAround(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method declaredMethod = pjp.getTarget().getClass().getDeclaredMethod(signature.getName(),
                signature.getMethod().getParameterTypes());
        CurrentDataSource annotation = declaredMethod.getAnnotation(CurrentDataSource.class);
        if (annotation != null) {
            if (annotation.name().equals(DynamicDataSource.MASTER)) {
                DynamicDataSource.context.set(annotation.name());
            } else if (annotation.name().equals(DynamicDataSource.SLAVE)) {
                DynamicDataSource.context.set(annotation.name());
            }
        }
    }
    
    public void afterDoAround() {
        DynamicDataSource.context.remove();
    }
}
