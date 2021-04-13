package com.sciatta.dev.java.example.aggregation;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Created by yangxiaoyu on 2019-04-20<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ListTests
 */
public class ListTests {
    @Test
    public void testArrayList() {
        List<Integer> l = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        assertEquals(1, (int) l.get(0));
    }
    
    @Test
    public void testDefaultList() {
        List<Integer> l = new ArrayList<>();    // 空列表，默认容量是DEFAULT_CAPACITY=10，懒汉模式分配内存
        l.add(1);
    }
    
    @Test
    public void testEmptyList() {
        List<Integer> l = new ArrayList<>(0);   // 初始容量是0
        l.add(1);
    }
    
    @Test
    public void testCapacity() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 第一次add，要求容量1，默认最小容量是10，取默认最小容量10
            l.add(i);
        }
        
        // 要求容量11（超过现有容量10），增长逻辑：现有容量+现有容量>>1，即15
        l.add(10);
        l.add(11);
        l.add(12);
        l.add(13);
        l.add(14);
        
        // 要求容量16（超过现有容量15），即15+7=22
        l.add(15);
        l.add(16);
    }
    
    @Test
    public void testLinkedList() {
        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        l.addFirst(10);
        
        assertEquals(10, (int) l.getFirst());
        assertEquals(0, (int) l.get(1));
    }
    
    @Test
    public void testVector() {
        Vector<Integer> vector = new Vector<>();    // synchronized this，性能低
        vector.add(1);
        System.out.println(vector);
    }
}
