package com.sciatta.dev.java.example.aggregation;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Created by yangxiaoyu on 2019-04-19<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * SetTests
 */
public class SetTests {

    @Test
    public void testHashSet() {
        Collection<String> src = Arrays.asList("h", "e", "l", "l", "o");

        // 无序，去重
        Set<String> c = new HashSet<>(src);
        assertEquals(4, c.size());

        assertEquals(new HashSet<>(Arrays.asList("h", "e", "l", "o")), c);
    }

    @Test
    public void testSetOperation() {
        Set<Integer> c1;
        Set<Integer> c2;

        // 并集union，两个集合中的全部元素
        c1 = new HashSet<>(Arrays.asList(1, 2, 5, 7, 6));
        c2 = new HashSet<>(Arrays.asList(3, 5, 1, 4, 9));
        c1.addAll(c2);
        assertEquals(new HashSet<>(Arrays.asList(1, 2, 5, 7, 6, 3, 4, 9)), c1);

        // 交集intersect，两个集合的公共元素
        c1 = new HashSet<>(Arrays.asList(1, 2, 5, 7, 6));
        c2 = new HashSet<>(Arrays.asList(3, 5, 1, 4, 9));
        c1.retainAll(c2);
        assertEquals(new HashSet<>(Arrays.asList(1, 5)), c1);

        // 差集difference，集合c1相对于c2的差集，即元素在c1中，不在c2中
        c1 = new HashSet<>(Arrays.asList(1, 2, 5, 7, 6));
        c2 = new HashSet<>(Arrays.asList(3, 5, 1, 4, 9));
        c1.removeAll(c2);
        assertEquals(new HashSet<>(Arrays.asList(2, 7, 6)), c1);

        // 对称差集symmetricDiff，集合c1和c2的并集，减去集合c1和c2的交集
        c1 = new HashSet<>(Arrays.asList(1, 2, 5, 7, 6));
        c2 = new HashSet<>(Arrays.asList(3, 5, 1, 4, 9));
        Set<Integer> symmetricDiff = new HashSet<>(c1);
        symmetricDiff.addAll(c2);
        Set<Integer> temp = new HashSet<>(c1);
        temp.retainAll(c2);
        symmetricDiff.removeAll(temp);
        assertEquals(new HashSet<>(Arrays.asList(2, 7, 6, 3, 4, 9)), symmetricDiff);
    }

    @Test
    public void testToArray() {
        Set<Integer> c1;
        c1 = new HashSet<>(Collections.singletonList(1000));
        Integer data = c1.iterator().next();

        // 返回的是一个新数组，集合不负责维护这个数组。
        Object[] os = c1.toArray();
        Object test = os[0];

        // 数组中的元素和集合中的元素相同。
        assertEquals(data, test);

        // 可以自由修改数组，而不影响原集合
        os[0] = null;
        assertEquals(1000, (int) c1.iterator().next());
    }

    @Test
    public void testEnumSet() {
        EnumSet<Color> colors = EnumSet.noneOf(Color.class);
        assertEquals(0, colors.size());
        colors.add(Color.RED);
        assertEquals(1, colors.size());

        colors = EnumSet.allOf(Color.class);
        assertEquals(3, colors.size());
        assertTrue(colors.contains(Color.BLUE));
        assertTrue(colors.contains(Color.RED));
        assertTrue(colors.contains(Color.GREEN));
    }

    enum Color {
        RED,
        GREEN,
        BLUE
    }
}
