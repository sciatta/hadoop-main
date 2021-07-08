package com.sciatta.dev.java.example.aggregation;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Created by yangxiaoyu on 2019-04-20<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ArrayListTests
 */
public class ArrayListTests {
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
    public void testVector() {
        // vector虽然每个方法都是同步的，但对于客户端调用vector的多个同步方法（多个同步方法的符合操作）并不是同步的，会导致数据不一致
        Vector<Integer> vector = new Vector<>();    // synchronized this，性能低
        vector.add(1);
        System.out.println(vector);
    }
    
    @Test
    public void testVectorToArray() {
        Vector<Integer> vector = new Vector<>();
        Integer a = new Integer(1000);
        Integer b = new Integer(1001);
        vector.add(a);
        vector.add(b);
        
        Integer a1 = vector.get(0);
        Integer b1 = vector.get(1);
        
        assertSame(a, a1);
        assertSame(b, b1);
    
        // 拷贝一份新的指针
        Object[] newVector = vector.toArray();
        Object a2 = newVector[0];
        Object b2 = newVector[1];
        
        assertSame(a, a2);
        assertSame(b, b2);
        // 改变新指针
        a2 = b2;
        assertSame(a2, b2);
    
        // 旧指针位置不变
        assertSame(a, a1);
        assertSame(b, b1);
    }
}
