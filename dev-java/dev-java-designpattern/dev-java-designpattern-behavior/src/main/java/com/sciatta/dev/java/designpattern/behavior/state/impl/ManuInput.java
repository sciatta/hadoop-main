package com.sciatta.dev.java.designpattern.behavior.state.impl;

import com.sciatta.dev.java.designpattern.behavior.state.AbstractDocState;

/**
 * Created by yangxiaoyu on 2021/7/5<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 人工录入
 */
public class ManuInput extends AbstractDocState {
    public ManuInput(StateManager stateManager) {
        super(stateManager);
    }
    
    @Override
    public void success() {
        this.stateManager.setCurrentState(new AutoProve(stateManager));
    }
    
    @Override
    public void fail() {
        this.stateManager.setCurrentState(new Untreated(stateManager));
    }
}
