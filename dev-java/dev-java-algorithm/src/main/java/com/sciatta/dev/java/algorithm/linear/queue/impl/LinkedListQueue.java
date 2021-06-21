package com.sciatta.dev.java.algorithm.linear.queue.impl;

/**
 * Created by yangxiaoyu on 2021/2/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LinkedListQueue 无界链表队列
 */
public class LinkedListQueue<T> {
    private Node<T> head, tail;
    
    static class Node<T> {
        T data;
        Node<T> next;
        
        public Node(T data) {
            this.data = data;
        }
    }
    
    public boolean enqueue(T data) {
        Node<T> node = new Node<>(data);
        
        if (head == null) {
            // 入队第一个节点
            head = tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        
        return true;    // 永远都不会满
    }
    
    public T dequeue() {
        T ret = null;
        Node delete;
        
        if (!empty()) {
            delete = head;
            ret = head.data;
            head = head.next;
            
            delete = null; // help gc
        }
        return ret;
    }
    
    public boolean empty() {
        return head == null;
    }
    
    public boolean full() {
        return false;
    }
}
