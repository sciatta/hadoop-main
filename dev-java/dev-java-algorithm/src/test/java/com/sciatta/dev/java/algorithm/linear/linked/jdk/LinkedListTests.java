package com.sciatta.dev.java.algorithm.linear.linked.jdk;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/1/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LinkedListTests
 */
public class LinkedListTests {
    @Test
    public void testAdd(){
        List<Integer> list = new LinkedList<>();
        
        list.add(2);
        list.add(4);
        list.add(9);
        list.add(1);
    
        System.out.println(list);
    }
}
