package com.sciatta.dev.java.designpattern.behavior.command;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AbstractCommand
 */
public abstract class AbstractCommand implements Command {
    protected DataBase dataBase;
    
    public AbstractCommand(DataBase dataBase) {
        this.dataBase = dataBase;
    }
}
