package com.sciatta.dev.java.designpattern.creative.singleton;

/**
 * Created by yangxiaoyu on 2021/6/22<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Hungary
 */
public class Hungary {
    
    private static final Hungary instance = new Hungary();
    
    private Hungary() {
    
    }
    
    public static Hungary getInstance() {
        return instance;
    }
}
