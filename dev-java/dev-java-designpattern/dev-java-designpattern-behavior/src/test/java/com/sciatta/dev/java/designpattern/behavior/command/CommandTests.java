package com.sciatta.dev.java.designpattern.behavior.command;

import com.sciatta.dev.java.designpattern.behavior.command.impl.AddCommand;
import com.sciatta.dev.java.designpattern.behavior.command.impl.CommandFactory;
import com.sciatta.dev.java.designpattern.behavior.command.impl.QueryCommand;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CommandTests
 */
public class CommandTests {
    @Test
    public void testAddCommand() {
        Command command = CommandFactory.getCommand(CommandFactory.CommandType.ADD);
        assertTrue(command instanceof AddCommand);
        command.execute();
    }
    
    @Test
    public void testQueryCommand() {
        Command command = CommandFactory.getCommand(CommandFactory.CommandType.QUERY);
        assertTrue(command instanceof QueryCommand);
        command.execute();
    }
}
