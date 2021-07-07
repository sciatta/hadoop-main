package com.sciatta.dev.java.designpattern.behavior.mediator;

import com.sciatta.dev.java.designpattern.behavior.mediator.impl.ServiceMediator;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Mediator
 */
public interface Mediator {
    Object handleService(ServiceMediator.ServiceName serviceName, String methodName, Class<?>[] paramClasses, Object[] params);
}
