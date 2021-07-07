package com.sciatta.dev.java.designpattern.behavior.mediator;

import com.sciatta.dev.java.designpattern.behavior.mediator.impl.ServiceMediator;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/7/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MediatorTests
 */
public class MediatorTests {
    private final Mediator god = new ServiceMediator();
    
    @Test
    public void testAddUser() {
        
        god.handleService(ServiceMediator.ServiceName.PassportController, "addUser",
                new Class[]{String.class, String.class}, new Object[]{"root", "root"});
    }
    
    @Test
    public void testNotCanLogin() {
        boolean test = (boolean) god.handleService(ServiceMediator.ServiceName.PassportController, "canLogin",
                new Class[]{String.class, String.class}, new Object[]{"root", "root"});
        assertFalse(test);
    }
    
    @Test
    public void testCanLogin() {
        testAddUser();
        
        boolean test = (boolean) god.handleService(ServiceMediator.ServiceName.PassportController, "canLogin",
                new Class[]{String.class, String.class}, new Object[]{"root", "root"});
        assertTrue(test);
    }
}
