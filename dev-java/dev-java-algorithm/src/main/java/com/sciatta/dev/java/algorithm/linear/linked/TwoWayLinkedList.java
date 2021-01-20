package com.sciatta.dev.java.algorithm.linear.linked;

import com.sciatta.dev.java.algorithm.linear.Linear;

/**
 * Created by yangxiaoyu on 2020/10/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 双向链表
 */
public class TwoWayLinkedList<T> implements Linear<T> {
    private Node<T> head;
    private int count;
    
    public TwoWayLinkedList() {
        head = new Node<>();
        count = 0;
    }
    
    public Node<T> getHead() {
        return head;
    }
    
    public int getCount() {
        return count;
    }
    
    @Override
    public void insert(T data) {
        Node<T> node = new Node<>(data);
        linkNode(head, node);
        count++;
    }
    
    @Override
    public void insert(T data, int i) {
        if (i > count) throw new IllegalArgumentException("插入位置不合法 " + i);
        
        if (i == 0) {
            insert(data);
            return;
        }
        
        Node<T> cur = head;
        
        for (int j = 0; j < i; j++) {
            cur = cur.next;
        }
        
        Node<T> node = new Node<>(data);
        linkNode(cur, node);
        count++;
    }
    
    private void linkNode(Node<T> cur, Node<T> node) {
        Node<T> temp = cur.next;
        
        cur.next = node;
        
        node.pre = cur;
        node.next = temp;
        
        if (temp != null) {
            temp.pre = node;
        }
    }
    
    @Override
    public void delete(T data) {
        Node<T> cur = head.next;
        
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
    
    public static class Node<T> {
        T value;
        Node<T> next;
        Node<T> pre;
        
        public Node() {
        }
        
        public Node(T value) {
            this.value = value;
        }
        
        public T getValue() {
            return value;
        }
        
        public void setValue(T value) {
            this.value = value;
        }
        
        public Node<T> getNext() {
            return next;
        }
        
        public void setNext(Node<T> next) {
            this.next = next;
        }
        
        public Node<T> getPre() {
            return pre;
        }
        
        public void setPre(Node<T> pre) {
            this.pre = pre;
        }
    }
}
