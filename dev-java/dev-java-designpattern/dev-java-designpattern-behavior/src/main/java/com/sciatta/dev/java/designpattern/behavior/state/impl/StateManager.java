package com.sciatta.dev.java.designpattern.behavior.state.impl;

import com.sciatta.dev.java.designpattern.behavior.state.DocState;

/**
 * Created by yangxiaoyu on 2021/7/5<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 状态管理
 */
public class StateManager implements DocState {
    private DocState currentState;
    
    public StateManager() {
        this.currentState = new AutoVerify(this);
    }
    
    public DocState getCurrentState() {
        return currentState;
    }
    
    protected void setCurrentState(DocState currentState) {
        this.currentState = currentState;
    }
    
    @Override
    public void success() {
        currentState.success();
    }
    
    @Override
    public void fail() {
        currentState.fail();
    }
}
