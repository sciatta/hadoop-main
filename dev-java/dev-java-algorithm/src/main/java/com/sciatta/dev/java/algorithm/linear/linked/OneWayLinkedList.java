package com.sciatta.dev.java.algorithm.linear.linked;

import com.sciatta.dev.java.algorithm.linear.Linear;

/**
 * Created by yangxiaoyu on 2020/10/11<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 单向链表
 */
public class OneWayLinkedList<T> implements Linear<T> {
    private Node<T> head;
    private int count;
    
    public OneWayLinkedList() {
        head = new Node<>();    // 哨兵，解决边界判断问题
    }
    
    /**
     * 在链表头插入元素
     *
     * @param data
     */
    @Override
    public void insert(T data) {
        Node<T> node = new Node<>(data);
        
        node.next = head.next;
        head.next = node;
        
        count++;
    }
    
    /**
     * 在指定位置之前插入元素
     *
     * @param data
     * @param i
     */
    @Override
    public void insert(T data, int i) {
        if (i > count) throw new IllegalArgumentException("插入位置不合法 " + i);
        
        if (i == 0) {
            insert(data);   // 插入到头节点之后
            return;
        }
        
        Node<T> pre = head;
        for (int j = 0; j < i; j++) {
            pre = pre.next; // 找到待插入位置的前驱节点
        }
        
        Node<T> node = new Node<>(data);
        node.next = pre.next;
        pre.next = node;
        
        count++;
    }
    
    @Override
    public void delete(T data) {
        Node<T> pre = head;
        Node<T> cur = head.next;
        
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

    public void deleteLast() {
    
    }
    
    @Override
    public int find(T data) {
        int index = 0;
        
        Node<T> cur = head.next;
        
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
    
    public T findRealData(T data) {
        Node<T> cur = head.next;
    
        while (cur != null) {
            if (cur.value != null && cur.value.equals(data)) {
                return cur.value;
            } else {
                cur = cur.next;
            }
        }
        return null;
    }
    
    static class Node<T> {
        T value;
        Node<T> next;
        
        public Node() {
        }
        
        public Node(T value) {
            this.value = value;
        }
    }
    
    @Override
    public T[] toArray() {
        Object[] ret = new Object[count];
        Node<T> cur = head;
        int index = 0;
        
        while (cur.next != null) {
            cur = cur.next;
            ret[index++] = cur.value;
        }
        
        return (T[]) ret;
    }
    
    public Node<T> getHead() {
        return head;
    }
    
    public int getCount() {
        return count;
    }
}
