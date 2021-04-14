package com.sciatta.dev.java.example.aggregation;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangxiaoyu on 2019-04-25<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * MapTests
 */
public class MapTests {
    @Test
    public void testMap() {
        // 1.7 数组+链表
        // 1.8 数组+链表/红黑树
        Map<String, Integer> parameters = new HashMap<>(getDefaultsMap());
        parameters.putAll(getOverridesMap());
        
        for (String key : parameters.keySet()) {
            if (key.equals("one")) {
                assertEquals(11, (int) parameters.get(key));
            } else if (key.equals("two")) {
                assertEquals(2, (int) parameters.get(key));
            }
        }
        // 重要方法
        // resize 分配默认容量或扩容，注意扩容需要rehash，影响性能
    }
    
    private Map<String, Integer> getDefaultsMap() {
        Map<String, Integer> defaults = new HashMap<>();
        defaults.put("one", 1);
        defaults.put("two", 2);
        return defaults;
    }
    
    private Map<String, Integer> getOverridesMap() {
        Map<String, Integer> defaults = new HashMap<>();
        defaults.put("one", 11);
        return defaults;
    }
    
    @Test
    public void testHashtable() {
        Hashtable<String, String> hashtable = new Hashtable<>();    // synchronized this，性能低
        hashtable.put("a", "1");
        hashtable.put("b", "2");
        System.out.println(hashtable);
    }
    
    @Test
    public void testConcurrentHashMap() {
        // 1.7  分段锁，Segment 数组（不可扩容） + HashEntry 数组（可扩容） + 链表；对segment加锁，segment默认16，最大并发线程数16
        // 1.8
        //      初始化：延迟初始化table，自旋+CAS
        //      Put: 自旋+CAS / synchronized 第一个节点（涉及锁升级知识，包括：无锁、偏向锁、自旋锁、重量锁）
        ConcurrentHashMap<Character, Character> hashMap = new ConcurrentHashMap<>();
        hashMap.put(new Character('a'), new Character('a'));
        hashMap.put(new Character('b'), new Character('b'));
        System.out.println(hashMap);
    }
}
