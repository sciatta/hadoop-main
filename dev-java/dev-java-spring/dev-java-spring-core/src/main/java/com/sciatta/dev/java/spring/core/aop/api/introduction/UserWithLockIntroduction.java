package com.sciatta.dev.java.spring.core.aop.api.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by yangxiaoyu on 2020/11/29<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserWithLockIntroduction
 */
public class UserWithLockIntroduction extends DelegatingIntroductionInterceptor implements Lockable {
    private boolean locked;
    
    @Override
    public void lock() {
        locked = true;
    }
    
    @Override
    public void unLock() {
        locked = false;
    }
    
    @Override
    public boolean isLock() {
        return locked;
    }
    
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (isLock() && mi.getMethod().getName().startsWith("set")) {
            throw new RuntimeException(mi.getMethod().getName() + " has value");
        }
        return super.invoke(mi);
    }
}
