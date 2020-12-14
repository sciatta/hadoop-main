package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.scan.tasks;

import com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.scan.AbstractBean;
import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2018/9/20<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * TaskTests
 */
public class TaskTests extends AbstractBean {
    @Before
    public void init() {
        initBeans(TaskConfig.class);
        printBeanNames();
    }

    @Test
    public void testTaskController() {
        String taskName = "one";
        TaskController taskController = applicationContext.getBean("taskController", TaskController.class);
        Task task = taskController.findTaskByName(taskName);

        assertNotNull(task);
        assertEquals(taskName, task.getName());
    }
}
