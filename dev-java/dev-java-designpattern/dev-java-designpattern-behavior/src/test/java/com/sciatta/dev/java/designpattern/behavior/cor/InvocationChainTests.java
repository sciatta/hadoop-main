package com.sciatta.dev.java.designpattern.behavior.cor;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2021/7/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * InvocationChainTests
 */
public class InvocationChainTests {
    @Test
    public void testInvocationChain() {
        InvocationChain chain = new InvocationChain();
        chain.addHandler(new CheckNameHandler());
        chain.addHandler(new CheckPasswordHandler());
        
        chain.invoke();
    }
}
