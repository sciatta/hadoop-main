package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.scan.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangxiaoyu on 2018/9/20<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * TaskServiceImpl
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task findTaskByName(String name) {
        return taskRepository.findTaskByName(name);
    }
}
