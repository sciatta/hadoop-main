package com.sciatta.hadoop.java.algorithm.linear;

/**
 * Created by yangxiaoyu on 2020/10/9<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Linear 线性表接口
 */
public interface Linear<T> {
    /**
     * 插入元素
     *
     * @param data
     */
    void insert(T data);

    /**
     * 在指定位置插入元素
     *
     * @param data
     * @param i
     */
    void insert(T data, int i);

    /**
     * 删除元素
     *
     * @param data
     */
    void delete(T data);

    /**
     * 查找元素
     *
     * @param data
     * @return -1 表示没有找到，其他 表示索引位置
     */
    int find(T data);

    /**
     * 仅用于测试
     *
     * @return
     */
    T[] toArray();
}