package com.sciatta.dev.java.spring.core.ioc.applicationcontext.scan.tasks;

/**
 * Created by yangxiaoyu on 2018/9/20<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * TaskService
 */
public interface TaskService {
    Task findTaskByName(String name);
}
