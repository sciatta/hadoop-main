package com.sciatta.dev.java.designpattern.behavior.template.inharit;

/**
 * Created by yangxiaoyu on 2021/7/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AbstractIfElsePrint
 */
public abstract class AbstractIfElsePrint {
    public void print() {
        if (ifElse()) {
            System.out.println("if");
        } else {
            System.out.println("else");
        }
    }
    
    protected abstract boolean ifElse();
}
