package com.sciatta.dev.java.designpattern.behavior.template.compose;

/**
 * Created by yangxiaoyu on 2021/7/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * IfElsePrint
 */
public class IfElsePrint {
    private IfElseListener listener;
    
    public interface IfElseListener {
        boolean ifElse();
    }
    
    public void addIfElseListener(IfElseListener listener) {
        this.listener = listener;
    }
    
    public void print() {
        if (listener == null) {
            return;
        }
        
        if (listener.ifElse()) {
            System.out.println("if");
        } else {
            System.out.println("else");
        }
    }
}
