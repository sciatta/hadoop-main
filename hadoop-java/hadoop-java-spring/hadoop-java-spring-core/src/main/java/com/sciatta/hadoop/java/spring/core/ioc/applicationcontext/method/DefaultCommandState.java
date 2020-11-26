package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.method;

/**
 * Created by yangxiaoyu on 2018/9/14<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * DefaultCommandState
 */
public class DefaultCommandState implements CommandState {
    public void execute() {
        // do nothing
        throw new RuntimeException("CommandState execute failed!");
    }
}
