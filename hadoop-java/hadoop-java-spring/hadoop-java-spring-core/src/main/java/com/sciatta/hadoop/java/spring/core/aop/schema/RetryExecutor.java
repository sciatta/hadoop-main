package com.sciatta.hadoop.java.spring.core.aop.schema;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by yangxiaoyu on 2020/11/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RetryExecutor
 */
public class RetryExecutor {
    private int retryNumber;
    
    public int getRetryNumber() {
        return retryNumber;
    }
    
    public void setRetryNumber(int retryNumber) {
        this.retryNumber = retryNumber;
    }
    
    public Object retry(ProceedingJoinPoint pjp, String userName) throws Throwable {
        int number = 0;
        Throwable e;
        do {
            number++;
            try {
                return pjp.proceed(new Object[]{userName});
            } catch (Throwable ex) {
                System.out.println("捕获到异常 " + ex + " 重试...");
                e = ex;
            }
        } while (number < retryNumber);
        
        // 重试全部失败，重新抛出异常
        throw e;
    }
}
