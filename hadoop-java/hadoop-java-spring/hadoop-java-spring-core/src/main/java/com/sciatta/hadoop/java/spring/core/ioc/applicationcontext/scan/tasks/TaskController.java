package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.scan.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by yangxiaoyu on 2018/9/20<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * TaskController
 */
@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    public Task findTaskByName(String name) {
        return taskService.findTaskByName(name);
    }
}
