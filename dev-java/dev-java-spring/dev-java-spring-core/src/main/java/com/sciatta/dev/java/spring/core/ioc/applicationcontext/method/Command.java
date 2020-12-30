package com.sciatta.dev.java.spring.core.ioc.applicationcontext.method;

/**
 * Created by yangxiaoyu on 2018/9/14<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Command
 */
public interface Command {
    public void execute();

    public void setCommandState(CommandState commandState);
}
