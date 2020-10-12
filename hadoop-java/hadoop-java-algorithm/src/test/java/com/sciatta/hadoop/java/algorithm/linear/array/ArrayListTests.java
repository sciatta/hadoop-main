package com.sciatta.hadoop.java.algorithm.linear.array;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/10/11<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ArrayList基于数组实现，可以动态扩充容量，但需要考虑当数据量大时，申请资源+拷贝所产生的性能问题
 */
public class ArrayListTests {
    @Test
    public void testInsert() {
        // 如果容量不够，则每次增加到1.5倍原有空间，同时将数据复制过去
        List<Integer> list = new ArrayList<>(2);

        // 2 => 1
        list.add(1);

        // 2 => 0
        list.add(2);

        // 2+2/2=3 => 0
        list.add(3);

        // 3+3/2=4 => 0
        list.add(4);
    }
}
