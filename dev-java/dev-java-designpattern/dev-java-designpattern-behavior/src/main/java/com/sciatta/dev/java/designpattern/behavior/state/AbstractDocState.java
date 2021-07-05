package com.sciatta.dev.java.designpattern.behavior.state;

import com.sciatta.dev.java.designpattern.behavior.state.impl.StateManager;

/**
 * Created by yangxiaoyu on 2021/7/5<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AbstractDocState
 */
public abstract class AbstractDocState implements DocState{
    protected StateManager stateManager;
    
    public AbstractDocState(StateManager stateManager) {
        this.stateManager = stateManager;
    }
}
