package com.sciatta.dev.java.designpattern.behavior.state.impl;

import com.sciatta.dev.java.designpattern.behavior.state.AbstractDocState;

/**
 * Created by yangxiaoyu on 2021/7/5<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 记账成功
 */
public class TallyFinish extends AbstractDocState {
    public TallyFinish(StateManager stateManager) {
        super(stateManager);
    }
    
    @Override
    public void success() {
    
    }
    
    @Override
    public void fail() {
    
    }
}
