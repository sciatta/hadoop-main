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
        Map<String, Integer> parameters = new HashMap<>(getDefaultsMap());
        parameters.putAll(getOverridesMap());
        
        for (String key : parameters.keySet()) {
            if (key.equals("one")) {
                assertEquals(11, (int) parameters.get(key));
            } else if (key.equals("two")) {
                assertEquals(2, (int) parameters.get(key));
            }
        }
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
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();
        hashMap.put("a", "1");
        hashMap.put("b", "2");
        System.out.println(hashMap);
    }
}
