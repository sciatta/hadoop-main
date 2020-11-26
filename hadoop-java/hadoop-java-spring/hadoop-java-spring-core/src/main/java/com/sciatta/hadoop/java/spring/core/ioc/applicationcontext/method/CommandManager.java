package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.method;

/**
 * Created by yangxiaoyu on 2018/9/14<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * CommandManager
 */
public abstract class CommandManager {
    private Command command;

    public void process(CommandState commandState) {
        command = createCommand();
        command.setCommandState(commandState);
        command.execute();
    }

    public Command getCommand() {
        return command;
    }

    // 方法注入
    protected abstract Command createCommand();
}
