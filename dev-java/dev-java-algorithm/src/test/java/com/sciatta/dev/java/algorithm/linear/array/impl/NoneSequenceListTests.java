package com.sciatta.dev.java.algorithm.linear.array.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2020/10/10<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * NoneSequenceListTests
 */
public class NoneSequenceListTests extends SequenceListTests {
    public void testInsert() {
        SequenceList sequenceList = getSequenceList();
        assertArrayEquals(formatArrayData(new Integer[]{1, 2, 8, 4}, MAXSIZE), sequenceList.toArray());

        sequenceList.insert(10, 1);
        assertArrayEquals(formatArrayData(new Integer[]{1, 10, 8, 4, 2}, MAXSIZE), sequenceList.toArray());

        sequenceList.insert(20, 5);
        assertArrayEquals(formatArrayData(new Integer[]{1, 10, 8, 4, 2, 20}, MAXSIZE), sequenceList.toArray());

        sequenceList.insert(40, 1);
        assertArrayEquals(formatArrayData(new Integer[]{1, 40, 8, 4, 2, 20, 10}, MAXSIZE), sequenceList.toArray());
    }

    @Test
    public void testNoDelete() {
        SequenceList sequenceList = getSequenceList();
        assertArrayEquals(formatArrayData(new Integer[]{1, 2, 8, 4}, MAXSIZE), sequenceList.toArray());

        sequenceList.delete(9);
        assertArrayEquals(formatArrayData(new Integer[]{1, 2, 8, 4}, MAXSIZE), sequenceList.toArray());
    }

    @Test
    public void testDelete() {
        SequenceList sequenceList = getSequenceList();
        assertArrayEquals(formatArrayData(new Integer[]{1, 2, 8, 4}, MAXSIZE), sequenceList.toArray());

        sequenceList.insert(10);
        sequenceList.insert(20);
        sequenceList.insert(80);
        sequenceList.insert(40);
        sequenceList.insert(60);
        sequenceList.insert(70);
        assertArrayEquals(formatArrayData(new Integer[]{1, 2, 8, 4, 10, 20, 80, 40, 60, 70}, MAXSIZE), sequenceList.toArray());
        assertEquals(10, sequenceList.count);   // 10/10

        sequenceList.delete(2);
        assertArrayEquals(formatArrayData(new Integer[]{1, 8, 4, 10, 20, 80, 40, 60, 70}, MAXSIZE), sequenceList.toArray());
        assertEquals(9, sequenceList.count);    // 触发delete碎片整理 9/9

        sequenceList.delete(10);
        sequenceList.delete(20);
        sequenceList.delete(80);
        sequenceList.delete(40);
        sequenceList.delete(60);
        sequenceList.delete(70);
        assertArrayEquals(formatArrayData(new Integer[]{1, 8, 4}, MAXSIZE), sequenceList.toArray());
        assertEquals(9, sequenceList.count);    // 有碎片  9/3

        sequenceList.insert(100);   // 触发insert碎片整理 4/4
        assertArrayEquals(formatArrayData(new Integer[]{1, 8, 4, 100}, MAXSIZE), sequenceList.toArray());
        assertEquals(4, sequenceList.count);

        sequenceList.insert(99, 0);
        assertArrayEquals(formatArrayData(new Integer[]{99, 8, 4, 100, 1}, MAXSIZE), sequenceList.toArray());
        assertEquals(5, sequenceList.count);    // 5/5
    }

    protected SequenceList getSequenceList() {
        SequenceList sequenceList = new NoneSequenceList(10);
        sequenceList.insert(1);
        sequenceList.insert(2);
        sequenceList.insert(8);
        sequenceList.insert(4);
        return sequenceList;
    }
}
