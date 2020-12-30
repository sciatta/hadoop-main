package com.sciatta.dev.java.algorithm.linear.array;

import com.sciatta.dev.java.algorithm.linear.Linear;

/**
 * Created by yangxiaoyu on 2020/10/9<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 顺序表，逻辑有序（注意和元素值有序之间的区别）
 */
public class SequenceList implements Linear<Integer> {
    protected Integer[] datas;
    protected int length;
    protected int count;

    public SequenceList(int maxSize) {
        this.length = maxSize;
        datas = new Integer[this.length];
        count = 0;
    }

    @Override
    public void insert(Integer data) {
        if (count >= length) {
            throw new IndexOutOfBoundsException("超出最大长度 " + length);
        }

        // 不指定插入位置情况下，默认在最后插入
        datas[count] = data;
        count++;
    }

    /**
     * 在指定位置插入元素，最好时间复杂度O(1)，最坏时间复杂度是O(N)
     *
     * @param data
     * @param i
     */
    @Override
    public void insert(Integer data, int i) {
        if (i > count) {
            throw new IllegalArgumentException("插入元素位置不合法 " + i);
        }

        if (count == length) {
            throw new IndexOutOfBoundsException("超出最大长度 " + length);
        }

        // 后移元素，保证逻辑有序
        for (int j = count - 1; j >= i; j--) {
            datas[j + 1] = datas[j];
        }

        datas[i] = data;
        count++;
    }

    /**
     * 最好时间复杂度O(1)，最坏时间复杂度是O(N)
     *
     * @param data
     */
    @Override
    public void delete(Integer data) {
        int i = find(data);

        if (i == -1) {
            System.out.println("没有找到待删除数据 " + data);
            return;
        }

        // 前移元素，覆盖待删除元素
        for (int j = i + 1; j < count; j++) {
            datas[j - 1] = datas[j];
        }

        datas[--count] = null;
    }

    @Override
    public int find(Integer data) {
        for (int i = 0; i < count; i++) {
            if (datas[i] != null && datas[i].equals(data)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Integer[] toArray() {
        return datas;
    }
}
