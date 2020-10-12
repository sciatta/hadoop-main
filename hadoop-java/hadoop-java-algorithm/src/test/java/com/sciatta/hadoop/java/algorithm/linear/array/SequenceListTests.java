package com.sciatta.hadoop.java.algorithm.linear.array;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2020/10/10<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SequenceListTests
 */
public class SequenceListTests {
    protected final int MAXSIZE = 10;

    @Test
    public void testInsert() {
        SequenceList sequenceList = getSequenceList();
        assertArrayEquals(formatArrayData(new Integer[]{1, 2, 8, 4}, MAXSIZE), sequenceList.toArray());

        sequenceList.insert(10, 0);
        assertArrayEquals(formatArrayData(new Integer[]{10, 1, 2, 8, 4}, MAXSIZE), sequenceList.toArray());

        sequenceList.insert(70, 3);
        assertArrayEquals(formatArrayData(new Integer[]{10, 1, 2, 70, 8, 4}, MAXSIZE), sequenceList.toArray());

        sequenceList.insert(90, 6);
        assertArrayEquals(formatArrayData(new Integer[]{10, 1, 2, 70, 8, 4, 90}, MAXSIZE), sequenceList.toArray());
    }

    @Test
    public void testDelete() {
        SequenceList sequenceList = getSequenceList();
        assertArrayEquals(formatArrayData(new Integer[]{1, 2, 8, 4}, MAXSIZE), sequenceList.toArray());

        sequenceList.delete(8);
        assertArrayEquals(formatArrayData(new Integer[]{1, 2, 4}, MAXSIZE), sequenceList.toArray());

        sequenceList.delete(1);
        assertArrayEquals(formatArrayData(new Integer[]{2, 4}, MAXSIZE), sequenceList.toArray());
    }

    @Test
    public void testFind() {
        SequenceList sequenceList = getSequenceList();
        assertArrayEquals(formatArrayData(new Integer[]{1, 2, 8, 4}, MAXSIZE), sequenceList.toArray());

        assertEquals(-1, sequenceList.find(9));

        assertEquals(1, sequenceList.find(2));
    }

    protected SequenceList getSequenceList() {
        SequenceList sequenceList = new SequenceList(MAXSIZE);
        sequenceList.insert(1);
        sequenceList.insert(2);
        sequenceList.insert(8);
        sequenceList.insert(4);
        return sequenceList;
    }

    protected Integer[] formatArrayData(Integer[] data, int length) {
        return Arrays.copyOf(data, length);
    }
}
