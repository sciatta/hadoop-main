package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.method;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangxiaoyu on 2018/9/14<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * CommandTests
 */
public class CommandTests {
    ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("application-context-method.xml");
    }

    @Test
    public void testExecute() {
        CommandManager commandManager = applicationContext.getBean("commandManager", CommandManager.class);
        commandManager.process(new CommandState() {
            public void execute() {
                assertTrue(true);
            }
        });
        Command one = commandManager.getCommand();

        commandManager.process(new CommandState() {
            public void execute() {
                assertFalse(false);
            }
        });
        Command two = commandManager.getCommand();

        assertNotSame(one, two);
    }

    @Test
    public void testPrototype() {
        assertNotSame(applicationContext.getBean("command", Command.class), applicationContext.getBean("command", Command.class));

        assertSame(applicationContext.getBean("commandManager", CommandManager.class), applicationContext.getBean("commandManager", CommandManager.class));
    }
}
