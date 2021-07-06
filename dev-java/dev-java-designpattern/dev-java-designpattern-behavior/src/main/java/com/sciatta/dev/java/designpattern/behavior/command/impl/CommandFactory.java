package com.sciatta.dev.java.designpattern.behavior.command.impl;

import com.sciatta.dev.java.designpattern.behavior.command.Command;
import com.sciatta.dev.java.designpattern.behavior.command.DataBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CommandFactory
 */
public class CommandFactory {
    public enum CommandType {
        ADD,
        DELETE,
        MODIFY,
        QUERY
    }
    
    private static Map<CommandType, Command> commandMap = new HashMap<>();
    
    static {
        DataBase dataBase = new DataBase();
        commandMap.put(CommandType.ADD, new AddCommand(dataBase));
        commandMap.put(CommandType.DELETE, new DeleteCommand(dataBase));
        commandMap.put(CommandType.MODIFY, new ModifyCommand(dataBase));
        commandMap.put(CommandType.QUERY, new QueryCommand(dataBase));
    }
    
    public static Command getCommand(CommandType commandType) {
        return commandMap.get(commandType);
    }
}
