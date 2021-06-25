package com.sciatta.dev.java.designpattern.structure.flyweight;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BigSizeNumber
 */
public class BigSizeNumber {
    private static HashMap<Integer, BigSizeNumber> cache = new LinkedHashMap<>();
    private final int value;
    
    private BigSizeNumber(int value) {
        this.value = value;
    }
    
    static {
        for (int i = 0; i <= 9; i++) {
            cache.put(i, new BigSizeNumber(i)); // 类加载的时候初始化
        }
    }
    
    public int getValue() {
        return value;
    }
    
    public static BigSizeNumber valueOf(int i) {    // 工厂模式
        BigSizeNumber bigSizeNumber = cache.get(i);
        if (bigSizeNumber == null)
            throw new IllegalArgumentException("not supported this parameter " + i);
        
        return bigSizeNumber;
    }
}
