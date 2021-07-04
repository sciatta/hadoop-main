package com.sciatta.dev.java.designpattern.behavior.cor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * InvocationChain 通过递归方式实现
 */
public class InvocationChain {
    private final List<InvocationHandler> handlers = new ArrayList<>();
    private int currentIndex;
    
    public InvocationChain() {
        currentIndex = 0;
    }
    
    public void addHandler(InvocationHandler handler) {
        handlers.add(handler);
    }
    
    public void invoke() {
        if (currentIndex < handlers.size()) {
            InvocationHandler handler = handlers.get(currentIndex++);
            handler.handler(this);
        }
    }
}
