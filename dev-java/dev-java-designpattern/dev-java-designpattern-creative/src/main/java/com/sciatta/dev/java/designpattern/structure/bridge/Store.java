package com.sciatta.dev.java.designpattern.structure.bridge;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Store
 */
public interface Store {
    String put(String localPath);
    
    String get(String id);
}
