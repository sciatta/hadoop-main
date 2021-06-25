package com.sciatta.dev.java.designpattern.structure.decorator;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ActionImpl
 */
public class ActionImpl implements Action{
    @Override
    public void doSomething() {
        System.out.println("Action...");
    }
}
