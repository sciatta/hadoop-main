package com.sciatta.dev.java.designpattern.structure.proxy.staticproxy;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BasedInterfaceTests
 */
public class BasedInterfaceTests {
    @Test
    public void testService() {
        BasedInterface.Service service = new BasedInterface.UserServiceProxy(new BasedInterface.UserService());
        
        service.doSomething();
    }
}
