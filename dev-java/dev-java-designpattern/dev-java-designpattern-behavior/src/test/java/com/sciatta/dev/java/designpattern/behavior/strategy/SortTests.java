package com.sciatta.dev.java.designpattern.behavior.strategy;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/7/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * SortTests
 */
public class SortTests {
    @Test
    public void testSort() {
        Sort sort = SortFactory.getSort(SortFactory.SortEnum.BubbleSort);   // 策略使用
        sort.sort();
        assertTrue(sort instanceof BubbleSort);
    }
}
