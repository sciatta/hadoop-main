package com.sciatta.dev.java.algorithm.linear.linked.resolve;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/1/27<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 从尾部删除第N个节点，基于单链表实现
 */
public class DeleteNthNodeFromTail {
    private Node head, tail;
    
    static class Node {
        Integer v;
        Node next;
        
        public Node(Integer v) {
            this.v = v;
        }
    }
    
    public DeleteNthNodeFromTail(Integer[] array) {
        // 插入到尾部构造链表
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                head = makeNode(array[i]);
                tail = head;
            } else {
                tail.next = makeNode(array[i]);
                tail = tail.next;
            }
        }
    }
    
    public Node deleteNthNode(int nth) {
        Node fast = head;
        Node slow = head;
        Node preSlow = null;
        
        int step = 1;
        // fast先从头开始走n步，然后slow开始从头开始走，接着fast继续向前走一步，slow走一步，直到fast走到链表尾为止，slow指向的节点就是倒数第N个待删除的节点
        while (fast != null && step++ < nth) {
            fast = fast.next;
        }
        
        if (fast == null) {
            // 超出范围
            return null;
        } else {
            while (fast.next != null) {
                fast = fast.next;
                preSlow = slow;
                slow = slow.next;
            }
        }
        
        // slow就是待删除节点
        if (preSlow == null) {
            // 删除的是第一个节点，第一个节点没有前驱节点
            head = slow.next;
        } else {
            preSlow.next = slow.next;
        }
        
        return slow;
    }
    
    private Node makeNode(Integer v) {
        return new Node(v);
    }
    
    public Integer[] toArray() {
        List<Integer> list = new ArrayList<>();
        
        Node cur = head;
        while (cur != null) {
            list.add(cur.v);
            cur = cur.next;
        }
        
        return list.toArray(new Integer[0]);
    }
}
