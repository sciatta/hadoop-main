package com.sciatta.dev.java.designpattern.behavior.command.impl;

import com.sciatta.dev.java.designpattern.behavior.command.AbstractCommand;
import com.sciatta.dev.java.designpattern.behavior.command.DataBase;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DeleteCommand
 */
public class DeleteCommand extends AbstractCommand {
    public DeleteCommand(DataBase dataBase) {
        super(dataBase);
    }
    
    @Override
    public void execute() {
        dataBase.deleteData();
    }
}
