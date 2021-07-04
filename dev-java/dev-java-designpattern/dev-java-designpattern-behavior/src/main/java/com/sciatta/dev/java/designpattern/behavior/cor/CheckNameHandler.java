package com.sciatta.dev.java.designpattern.behavior.cor;

/**
 * Created by yangxiaoyu on 2021/7/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CheckNameHandler
 */
public class CheckNameHandler implements InvocationHandler{
    @Override
    public void handler(InvocationChain chain) {
        System.out.println("CheckNameHandler in");
    
        chain.invoke(); // 递归调用
        
        System.out.println("CheckNameHandler out");
    }
}
