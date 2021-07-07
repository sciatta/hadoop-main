package com.sciatta.dev.java.designpattern.behavior.mediator.impl;

import com.sciatta.dev.java.designpattern.behavior.mediator.Mediator;
import com.sciatta.dev.java.designpattern.behavior.mediator.PassportController;
import com.sciatta.dev.java.designpattern.behavior.mediator.UserRepository;
import com.sciatta.dev.java.designpattern.behavior.mediator.UserService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ServiceMediator
 */
public class ServiceMediator implements Mediator {
    public enum ServiceName {
        PassportController,
        UserService,
        UserRepository
    }
    
    private static final Map<ServiceName, Class<?>> serviceMap = new HashMap<>();
    
    static {
        serviceMap.put(ServiceName.PassportController, PassportControllerImpl.class);
        serviceMap.put(ServiceName.UserService, UserServiceImpl.class);
        serviceMap.put(ServiceName.UserRepository, UserRepositoryImpl.class);
    }
    
    @Override
    public Object handleService(ServiceName serviceName, String methodName, Class<?>[] paramClasses, Object[] params) {
        Object result = null;
        
        try {
            Class<?> serviceClass = serviceMap.get(serviceName);
            
            Object service = serviceClass.getConstructor(Mediator.class).newInstance(this);
            Method method = serviceClass.getMethod(methodName, paramClasses);
            
            result = method.invoke(service, params);
            
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
