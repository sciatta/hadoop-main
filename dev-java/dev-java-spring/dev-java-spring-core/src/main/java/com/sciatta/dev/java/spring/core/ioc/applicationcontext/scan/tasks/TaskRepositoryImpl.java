package com.sciatta.dev.java.spring.core.ioc.applicationcontext.scan.tasks;

import org.springframework.stereotype.Repository;

/**
 * Created by yangxiaoyu on 2018/9/20<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * TaskRepositoryImpl
 */
@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @Override
    public Task findTaskByName(String name) {
        return new Task(name);
    }
}
