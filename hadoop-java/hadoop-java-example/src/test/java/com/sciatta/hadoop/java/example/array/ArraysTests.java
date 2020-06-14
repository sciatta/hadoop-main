package com.sciatta.hadoop.java.example.array;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

/**
 * Created by yangxiaoyu on 2020/5/31<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ArraysTests
 */
public class ArraysTests {
    @Test
    public void testSort() {
        Integer[] test = getData();
        // 引用类型：TimSort 默认排序实现
        // 基本类型：Dual-Pivot Quicksort
        Arrays.sort(test);
        assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 9, 10}, test);
    }

    @Test
    public void testParallelSort() {
        Integer[] test = getData();
        // 并行归并排序，利用ForkJoinPool多线程并行计算
        Arrays.parallelSort(test);
        assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 9, 10}, test);
    }

    @Test
    public void testSortByComparator() {
        Integer[] test = getData();
        // 倒序排序
        Arrays.sort(test, (a, b) -> -(a - b));
        assertArrayEquals(getReverseData(new Integer[]{1, 2, 3, 5, 6, 9, 10}), test);
    }

    @Test
    public void testEquals() {
        boolean test = Arrays.equals(getData(), getData());
        assertTrue(test);
    }

    @Test
    public void testFill() {
        int[] arr = new int[5];
        // 可以用于初始化数组
        // [fromIndex, toIndex)
        Arrays.fill(arr, 0, arr.length, 0);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0}, arr);
    }

    @Test
    public void testCopyOfAndRange() {
        Integer[] test = getData();
        assertArrayEquals(new Integer[]{10, 2, 5, 6, 1, 9, 3}, test);

        // 新长度小于原数组长度时，截取指定长度
        Integer[] truncate = Arrays.copyOf(test, 2);
        assertArrayEquals(new Integer[]{10, 2}, truncate);

        // 新长度大于原数组长度时，除复制原数组元素外，之后元素对象赋值为null
        Integer[] padding = Arrays.copyOf(test, 8);
        assertArrayEquals(new Integer[]{10, 2, 5, 6, 1, 9, 3, null}, padding);

        // [from, to)
        Integer[] range = Arrays.copyOfRange(test, 2, 5);
        assertArrayEquals(new Integer[]{5, 6, 1}, range);
    }

    @Test
    public void testCopyOfValue() {
        class Cat {
            String name;

            Cat(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        Cat[] cats = new Cat[1];
        cats[0] = new Cat("Miao");

        // 创建新数组
        Cat[] cloneCats = Arrays.copyOf(cats, cats.length);

        assertNotSame(cats, cloneCats); // 复制后，数组对象不同
        assertSame(cats[0], cloneCats[0]);   // 数组引用的对象相同

        cloneCats[0].setName("XiaoDingDang");  // 修改复制后的数组引用对象，原数组的引用对象也会修改。因为引用的是同一个对象
        assertEquals("XiaoDingDang", cloneCats[0].getName());
        assertEquals("XiaoDingDang", cats[0].getName());
    }

    @Test
    public void testBinarySearch() {
        int key = 5;
        Integer[] test = getData();
        Arrays.sort(test);
        int index = Arrays.binarySearch(test, key);    // 二分查找，第一个参数必须是已经排序后的数组
        assertEquals(3, index);
    }

    private static Integer[] getData() {
        return new Integer[]{10, 2, 5, 6, 1, 9, 3};
    }

    private static Integer[] getReverseData(Integer[] source) {
        List<Integer> temp = Arrays.asList(source);
        Collections.reverse(temp);
        return temp.toArray(new Integer[0]);
    }
}
