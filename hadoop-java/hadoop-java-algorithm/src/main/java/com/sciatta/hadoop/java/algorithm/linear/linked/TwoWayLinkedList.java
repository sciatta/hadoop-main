package com.sciatta.hadoop.java.algorithm.linear.linked;

import com.sciatta.hadoop.java.algorithm.linear.Linear;

/**
 * Created by yangxiaoyu on 2020/10/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 双向链表
 */
public class TwoWayLinkedList implements Linear<Integer> {
    private Node head;
    private int count;

    public TwoWayLinkedList() {
        head = new Node();
        count = 0;
    }

    @Override
    public void insert(Integer data) {
        Node node = new Node(data);
        linkNode(head, node);
        count++;
    }

    @Override
    public void insert(Integer data, int i) {
        if (i > count) throw new IllegalArgumentException("插入位置不合法 " + i);

        if (i == 0) {
            insert(data);
            return;
        }

        Node cur = head;

        for (int j = 1; j <= i; j++) {
            cur = cur.next;
        }

        Node node = new Node(data);
        linkNode(cur, node);
        count++;
    }

    private void linkNode(Node cur, Node node) {
        Node temp = cur.next;

        cur.next = node;

        node.pre = cur;
        node.next = temp;

        if (temp != null) {
            temp.pre = node;
        }
    }

    @Override
    public void delete(Integer data) {
        Node cur = head.next;

        while (cur != null) {
            if (cur.value != null && cur.value.equals(data)) {
                cur.pre.next = cur.next;
                if (cur.next != null) cur.next.pre = cur.pre;
                cur.next = null;
                cur.pre = null;
                count--;
                break;
            } else {
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

    static class Node {
        Integer value;
        Node next;
        Node pre;

        public Node() {
        }

        public Node(Integer value) {
            this.value = value;
        }
    }
}
