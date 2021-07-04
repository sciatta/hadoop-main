package com.sciatta.dev.java.designpattern.behavior.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/7/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * SortFactory 策略创建
 */
public class SortFactory {
    enum SortEnum {
        QuickSort,
        BubbleSort
    }
    
    private static Map<SortEnum, Sort> sorts = new HashMap<>();
    
    static {
        sorts.put(SortEnum.QuickSort, new QuickSort());
        sorts.put(SortEnum.BubbleSort, new BubbleSort());
    }
    
    public static Sort getSort(SortEnum sortEnum) {
        return sorts.get(sortEnum);
    }
}
