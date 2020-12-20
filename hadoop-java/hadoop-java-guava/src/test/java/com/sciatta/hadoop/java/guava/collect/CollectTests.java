package com.sciatta.hadoop.java.guava.collect;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.*;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/12/20<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CollectTests
 */
public class CollectTests {
    @Test
    public void testList() {
        ArrayList<Integer> list = getList();
        List<List<Integer>> partition = Lists.partition(list, 3);   // 每一个分区3个元素
        print(partition);
    }
    
    @Test
    public void testMultiMap() {
        ArrayList<Integer> list = getList();
        // key和value都重复，保存新value和旧value到ArrayList中
        // key重复，value保存到ArrayList中
        ArrayListMultimap<Integer, Integer> multimap = ArrayListMultimap.create();
        
        list.forEach(a -> multimap.put(a, a + 1));
        print(multimap);
        
        multimap.put(1, 2);
        print(multimap);
        
        multimap.put(1, 3);
        print(multimap);
    }
    
    @Test
    public void testBiMap() {
        HashBiMap<Object, Object> map = HashBiMap.create(); // 双向Map
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        assertEquals(3, map.get("third"));
        assertEquals("third", map.inverse().get(3));
    
        // List -> Map
        ImmutableMap<Integer, Integer> toMap = Maps.toMap(getList(), a -> a + 1);
        print(toMap);
    }
    
    private ArrayList<Integer> getList() {
        return Lists.newArrayList(1, 2, 3, 4);
    }
    
    private void print(Object o) {
        System.out.println(JSON.toJSON(o));
    }
}
