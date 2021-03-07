package com.sciatta.dev.java.jvm.gc.overflow;

/**
 * Created by yangxiaoyu on 2021/3/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * StackOverFlow <br>
 * -Xss1m
 */
public class StackOverFlow {
    private int invokeTimes;
    
    public void invoke() {
        invokeTimes++;
        invoke();
    }
    
    public int getInvokeTimes() {
        return invokeTimes;
    }
    
    public static void main(String[] args) {
        StackOverFlow stackOverFlow = new StackOverFlow();
        try {
            stackOverFlow.invoke();
        } finally {
            System.out.println(stackOverFlow.invokeTimes);
        }
    }
}
