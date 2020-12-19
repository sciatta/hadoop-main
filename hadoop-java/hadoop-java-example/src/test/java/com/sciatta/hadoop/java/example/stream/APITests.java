package com.sciatta.hadoop.java.example.stream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by yangxiaoyu on 2020/12/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * APITests
 */
public class APITests {
    private List<Integer> list;
    
    @Before
    public void init() {
        list = Arrays.asList(9, 4, 2, 6, 3, 1, 6);
    }
    
    @Test
    public void testFindFirst() {
        int data = list.stream().findFirst().map(i -> i * 2).orElse(100);
        assertEquals(18, data);
    }
    
    @Test
    public void testOptional() {
        Integer data = (Integer) Optional.ofNullable(null).orElse(100);
        assertEquals(100, data.intValue());
    }
    
    @Test
    public void testReduce() {
        // reduce 设置初始值
        // a, b, c
        // x=0, y=a => result=x+y => x=result, y=b ....
        Integer reduce = list.stream().filter(i -> i > 4).distinct().reduce(0, (x, y) -> x + y);
        assertEquals(15, reduce.intValue());
    }
    
    @Test
    public void testToMap() {
        // toMap
        // 第一个参数 key
        // 第二个参数 value
        // 第三个参数 当key重复时，两个value如何取值的策略
        // 第四个参数 生成目标Map
        LinkedHashMap<Integer, Integer> map = list.parallelStream()
                .collect(Collectors.toMap(a -> a, a -> a + 1, (a, b) -> a, LinkedHashMap::new));
        System.out.println(map);
        
        map.forEach((k, v) -> System.out.println("key=" + k + " value=" + v));
        
        List<Integer> list = map.entrySet().parallelStream()
                .map(entry -> entry.getKey() + entry.getValue()).collect(Collectors.toList());
        System.out.println(list);
    }
}
