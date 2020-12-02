package com.sciatta.hadoop.java.spring.core.aop.api.introduction;

/**
 * Created by yangxiaoyu on 2020/11/29<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Lockable
 */
public interface Lockable {
    void lock();
    void unLock();
    boolean isLock();
}
