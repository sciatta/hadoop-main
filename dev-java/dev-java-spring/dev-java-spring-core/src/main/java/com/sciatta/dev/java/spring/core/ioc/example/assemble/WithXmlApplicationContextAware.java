package com.sciatta.dev.java.spring.core.ioc.example.assemble;

import com.sciatta.dev.java.spring.core.model.Name;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by yangxiaoyu on 2020/12/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * WithXmlApplicationContextAware
 */
public class WithXmlApplicationContextAware extends BaseBean implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Object lock = new Object();
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public Name getUser() {
        if (user == null) {
            synchronized (lock) {
                if (user == null) {
                    this.user = applicationContext.getBean("user", Name.class);
                }
            }
        }
        return user;
    }
}
