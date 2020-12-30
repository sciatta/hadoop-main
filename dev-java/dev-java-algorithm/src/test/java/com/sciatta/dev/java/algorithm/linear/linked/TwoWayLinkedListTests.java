package com.sciatta.dev.java.algorithm.linear.linked;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2020/10/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * TwoWayLinkedListTests
 */
public class TwoWayLinkedListTests {
    @Test
    public void testInsertHead() {
        TwoWayLinkedList list = newLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        assertArrayEquals(new Integer[]{3, 2, 1}, list.toArray());
    }

    @Test
    public void testInsert() {
        TwoWayLinkedList list = newLinkedList();
        list.insert(1);
        list.insert(2);

        list.insert(5, 0);
        list.insert(3, 2);
        list.insert(9, 4);

        assertArrayEquals(new Integer[]{5, 2, 3, 1, 9}, list.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertIllegal() {
        TwoWayLinkedList list = newLinkedList();
        list.insert(1);
        list.insert(2);

        list.insert(8, 3);
    }

    @Test
    public void testDelete() {
        TwoWayLinkedList list = newLinkedList();
        list.insert(1);
        list.insert(2);

        list.insert(5, 0);
        list.insert(3, 2);
        list.insert(9, 4);
        assertArrayEquals(new Integer[]{5, 2, 3, 1, 9}, list.toArray());

        list.delete(5);
        assertArrayEquals(new Integer[]{2, 3, 1, 9}, list.toArray());

        list.delete(3);
        assertArrayEquals(new Integer[]{2, 1, 9}, list.toArray());

        list.delete(9);
        assertArrayEquals(new Integer[]{2, 1}, list.toArray());

        list.delete(10);
        assertArrayEquals(new Integer[]{2, 1}, list.toArray());
    }

    @Test
    public void testFind() {
        TwoWayLinkedList list = newLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        assertEquals(0, list.find(3));
        assertEquals(1, list.find(2));
        assertEquals(2, list.find(1));

        assertEquals(-1, list.find(8));
    }

    private TwoWayLinkedList newLinkedList() {
        return new TwoWayLinkedList();
    }
}
