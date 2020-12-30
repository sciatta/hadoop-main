package com.sciatta.dev.java.algorithm.linear.linked;

import com.sciatta.dev.java.algorithm.linear.Linear;

/**
 * Created by yangxiaoyu on 2020/10/11<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 单向链表
 */
public class OneWayLinkedList implements Linear<Integer> {
    private Node head;
    private int count;

    public OneWayLinkedList() {
        head = new Node();    // 哨兵，解决边界判断问题
    }

    /**
     * 在链表头插入元素
     *
     * @param data
     */
    @Override
    public void insert(Integer data) {
        Node node = new Node(data);
        Node temp = head.next;
        head.next = node;
        node.next = temp;
        count++;
    }

    /**
     * 在指定位置之前插入元素
     *
     * @param data
     * @param i
     */
    @Override
    public void insert(Integer data, int i) {
        if (i > count) throw new IllegalArgumentException("插入位置不合法 " + i);

        Node pre = head;
        for (int j = 1; j <= i; j++) {
            pre = pre.next; // 找到待插入位置的前驱节点
        }

        Node node = new Node(data);
        Node temp = pre.next;
        pre.next = node;
        node.next = temp;
        count++;
    }

    @Override
    public void delete(Integer data) {
        Node pre = head;
        Node cur = head.next;

        while (cur != null) {
            if (cur.value != null && cur.value.equals(data)) {
                pre.next = cur.next;
                cur.next = null;
                count--;
                break;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
    }

    @Override
    public int find(Integer data) {
        int index = 0;

        Node cur = head.next;

        while (cur != null) {
            if (cur.value != null && cur.value.equals(data)) {
                return index;
            } else {
                cur = cur.next;
                index++;
            }
        }

        return -1;
    }

    static class Node {
        Integer value;
        Node next;

        public Node() {
        }

        public Node(Integer value) {
            this.value = value;
        }
    }

    @Override
    public Integer[] toArray() {
        Integer[] ret = new Integer[count];
        Node cur = head;
        int index = 0;

        while (cur.next != null) {
            cur = cur.next;
            ret[index++] = cur.value;
        }

        return ret;
    }
}
