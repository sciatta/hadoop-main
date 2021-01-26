package com.sciatta.dev.java.algorithm.linear.linked.resolve;

import com.sciatta.dev.java.algorithm.linear.linked.impl.OneWayLinkedList;

/**
 * Created by yangxiaoyu on 2021/1/21<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 单链表反转
 */
public class Reverse<T> {
    private OneWayLinkedList<T> list = new OneWayLinkedList<>();
    
    public Reverse(T[] array) {
        for (int i = 0; i < array.length; i++) {
            list.insert(array[i], i);
        }
    }
    
    public T[] toArray() {
        return list.toArray();
    }
    
    public T[] doReverse() {
        OneWayLinkedList.Node<T> head = list.getHead();
        OneWayLinkedList.Node<T> pre = head.getNext();
        
        if (pre == null) {
            // 空链表
            return null;
        }
        
        OneWayLinkedList.Node<T> cur = pre.getNext();
        
        pre.setNext(null);  // 第一个节点反转后就是最后一个节点
        
        while (cur != null) {
            head.setNext(cur);
            cur = cur.getNext();
            head.getNext().setNext(pre);
            pre = head.getNext();
        }
        
        return list.toArray();
    }
}
