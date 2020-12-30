package com.sciatta.dev.java.algorithm.linear.linked;

import com.sciatta.dev.java.algorithm.linear.Linear;

/**
 * Created by yangxiaoyu on 2020/10/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 静态链表，基于数组实现<p></p>
 * 有两条链表，一条是备用链，作用是连接未使用的空间；另一条是链表，作用是连接链表的各个节点；数组的第一个元素不保存数据，作为备用
 * 链的头节点，数组的最后一个元素也不保存数据，作为链表的头结点
 */
public class StaticLinkedList implements Linear<Integer> {
    private Node[] nodes;
    private int length;
    private int count;

    public StaticLinkedList(int maxSize) {
        this.length = maxSize;
        nodes = new Node[this.length];

        // 初始化备用链
        for (int i = 0; i < length - 1; i++) {
            nodes[i] = new Node(null, i + 1);
        }

        // 初始化链表头结点
        nodes[length - 1] = new Node(null, 0);
    }

    private boolean isFull() {
        return nodes[0].cursor == length - 1;
    }

    private boolean isEmpty() {
        return nodes[length - 1].cursor == 0;
    }

    @Override
    public void insert(Integer data) {
        if (isFull()) throw new IllegalArgumentException("静态队列已满 " + count);

        // 备用链释放第一个节点
        int release = nodes[0].cursor;
        nodes[0].cursor = nodes[release].cursor;

        // 插入到链表的头节点之后
        nodes[release].cursor = nodes[length - 1].cursor;
        nodes[release].data = data;
        nodes[length - 1].cursor = release;

        count++;
    }

    @Override
    public void insert(Integer data, int i) {
        if (i > count) throw new IllegalArgumentException("插入位置不合法 " + i);

        if (isFull()) throw new IllegalArgumentException("静态队列已满 " + count);

        // 备用链释放第一个节点
        int release = nodes[0].cursor;
        nodes[0].cursor = nodes[release].cursor;

        int pre = length -1 ;
        for (int j = 1; j <= i; j++) {
            pre = nodes[pre].cursor;    // 找到前驱
        }

        // 指定位置插入节点
        nodes[release].cursor = nodes[pre].cursor;
        nodes[release].data = data;
        nodes[pre].cursor = release;

        count++;
    }

    @Override
    public void delete(Integer data) {
        if (isEmpty()) throw new IllegalArgumentException("静态队列为空 " + count);

        int pre = length - 1;
        int cur = nodes[length - 1].cursor;

        while (cur != 0) {
            if (data.equals(nodes[cur].data)) {
                // 从链表上删除此节点
                nodes[pre].cursor = nodes[cur].cursor;

                // 删除节点添加到备用链上
                nodes[cur].cursor = nodes[0].cursor;
                nodes[cur].data = null;
                nodes[0].cursor = cur;

                count--;
            } else {
                pre = cur;
                cur = nodes[cur].cursor;
            }
        }
    }

    @Override
    public int find(Integer data) {
        if (isEmpty()) return -1;

        int index = 0;
        int cur = nodes[length - 1].cursor;

        while (cur != 0) {
            if (data.equals(nodes[cur].data)) {
                return index;
            } else {
                index++;
                cur = nodes[cur].cursor;
            }
        }

        return -1;
    }

    @Override
    public Integer[] toArray() {
        if (isEmpty()) return new Integer[0];

        Integer[] ret = new Integer[count];

        int cur = nodes[length - 1].cursor;
        int index = 0;

        while (cur != 0) {
            ret[index++] = nodes[cur].data;
            cur = nodes[cur].cursor;
        }

        return ret;
    }

    static class Node {
        private Integer data;
        private int cursor;

        public Node(Integer data, int cursor) {
            this.data = data;
            this.cursor = cursor;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public int getCursor() {
            return cursor;
        }

        public void setCursor(int cursor) {
            this.cursor = cursor;
        }
    }
}
