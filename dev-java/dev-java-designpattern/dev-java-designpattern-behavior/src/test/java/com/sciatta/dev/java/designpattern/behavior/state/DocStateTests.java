package com.sciatta.dev.java.designpattern.behavior.state;

import com.sciatta.dev.java.designpattern.behavior.state.impl.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/7/5<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DocStateTests
 */
public class DocStateTests {
    @Test
    public void testTallyFlow() {
        StateManager manager = new StateManager();
        assertTrue(manager.getCurrentState() instanceof AutoVerify);
        
        manager.success();
        assertTrue(manager.getCurrentState() instanceof AutoProve);
        
        manager.success();
        assertTrue(manager.getCurrentState() instanceof AutoTally);
        
        manager.success();
        assertTrue(manager.getCurrentState() instanceof TallyFinish);
    }
    
    @Test
    public void testUntreatedFlow() {
        StateManager manager = new StateManager();
        assertTrue(manager.getCurrentState() instanceof AutoVerify);
        
        manager.success();
        assertTrue(manager.getCurrentState() instanceof AutoProve);
        
        manager.success();
        assertTrue(manager.getCurrentState() instanceof AutoTally);
        
        manager.fail();
        assertTrue(manager.getCurrentState() instanceof Untreated);
        
        manager.fail();
        assertTrue(manager.getCurrentState() instanceof ManuInput);
        
        manager.fail();
        assertTrue(manager.getCurrentState() instanceof Untreated);
        
        manager.success();
        assertTrue(manager.getCurrentState() instanceof UntreatedFinish);
    }
}
