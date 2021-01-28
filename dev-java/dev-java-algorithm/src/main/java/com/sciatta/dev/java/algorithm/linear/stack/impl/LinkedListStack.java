package com.sciatta.dev.java.algorithm.linear.stack.impl;

/**
 * Created by yangxiaoyu on 2021/1/28<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 基于链表的栈
 */
public class LinkedListStack<T> {
    private int count;
    private Node<T> head;
    
    static class Node<T> {
        T v;
        Node<T> next;
        
        public Node(T v) {
            this.v = v;
        }
    }
    
    public void push(T data) {
        Node<T> newData = new Node<>(data);
        
        // 插入头节点之后
        if (head == null) {
            head = newData;
        } else {
            newData.next = head;
            head = newData;
        }
        
        count++;
    }
    
    public T pop() {
        if (head == null) {
            return null;
        }
        
        Node<T> delete = head;
        head = head.next;
        count--;
        return delete.v;
    }
    
    public T peek() {
        if (head == null) {
            return null;
        }
        
        return head.v;
    }
    
    public int getCount() {
        return count;
    }
}
