package com.sciatta.dev.java.example.aggregation;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
}
