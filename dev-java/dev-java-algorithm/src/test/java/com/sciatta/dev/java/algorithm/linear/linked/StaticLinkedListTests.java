package com.sciatta.dev.java.algorithm.linear.linked;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by yangxiaoyu on 2020/10/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * StaticLinkedListTests
 */
public class StaticLinkedListTests {
    @Test
    public void testInsertHead() {
        StaticLinkedList list = newLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        assertArrayEquals(new Integer[]{3, 2, 1}, list.toArray());
    }

    @Test
    public void testInsert() {
        StaticLinkedList list = newLinkedList();
        list.insert(1);
        list.insert(2);

        list.insert(5, 0);
        list.insert(3, 2);
        list.insert(9, 4);

        assertArrayEquals(new Integer[]{5, 2, 3, 1, 9}, list.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertIllegal() {
        StaticLinkedList list = newLinkedList();
        list.insert(1);
        list.insert(2);

        list.insert(8, 3);
    }

    @Test
    public void testDelete() {
        StaticLinkedList list = newLinkedList();
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
        StaticLinkedList list = newLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        assertEquals(0, list.find(3));
        assertEquals(1, list.find(2));
        assertEquals(2, list.find(1));

        assertEquals(-1, list.find(8));
    }

    private StaticLinkedList newLinkedList() {
        return new StaticLinkedList(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoundary() {
        StaticLinkedList list = new StaticLinkedList(5);    // 只能插入n-2个元素
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.insert(4);
    }

    @Test
    public void testInsertAndDelete() {
        StaticLinkedList list = new StaticLinkedList(5);    // 只能插入n-2个元素
        list.insert(1);
        list.insert(2);
        list.insert(3); // full
        assertArrayEquals(new Integer[]{3, 2, 1}, list.toArray());

        list.delete(2);     // 删除中间元素
        assertArrayEquals(new Integer[]{3, 1}, list.toArray());

        list.insert(5);
        assertArrayEquals(new Integer[]{5, 3, 1}, list.toArray());

        list.delete(5);     // 删除第一个元素
        assertArrayEquals(new Integer[]{3, 1}, list.toArray());

        list.insert(9);
        assertArrayEquals(new Integer[]{9, 3, 1}, list.toArray());

        list.delete(1);     // 删除最后一个元素
        assertArrayEquals(new Integer[]{9, 3}, list.toArray());
    }
}
