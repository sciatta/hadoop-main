package com.sciatta.dev.java.concurrency.aqs.mylock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by yangxiaoyu on 2021/4/29<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MyLock
 */
public class MyLock extends AbstractQueuedSynchronizer {
    public void lock() {
        acquire(1);
    }
    
    public void unlock() {
        release(1);
    }
    
    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) { // 并发冲突
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        
        return false;
    }
    
    @Override
    protected boolean tryRelease(int arg) {
        int state = getState() - arg;
        
        if (!isHeldExclusively())
            throw new IllegalMonitorStateException();
        
        if (state == 0) {
            setExclusiveOwnerThread(null);
        }
        setState(state);    // 没有并发冲突
        return true;
    }
    
    @Override
    protected boolean isHeldExclusively() {
        return getExclusiveOwnerThread() == Thread.currentThread();
    }
}
