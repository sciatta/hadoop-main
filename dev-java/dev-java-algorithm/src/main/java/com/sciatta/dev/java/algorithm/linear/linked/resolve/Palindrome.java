package com.sciatta.dev.java.algorithm.linear.linked.resolve;

import com.sciatta.dev.java.algorithm.linear.linked.impl.TwoWayLinkedList;

/**
 * Created by yangxiaoyu on 2021/1/19<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 判断是否是回文字符串，基于链表的快慢指针
 */
public class Palindrome<T> {
    private final TwoWayLinkedList<T> list = new TwoWayLinkedList<>();
    
    public Palindrome(T[] datas) {
        for (T data : datas) {
            list.insert(data);
        }
    }
    
    public boolean isPalindrome() {
        boolean end = false;    // fast是否走到终点
        TwoWayLinkedList.Node<T> slow, fast, back = null;
        fast = slow = list.getHead().getNext();
        
        if (fast == null) {
            // 空链表
            return false;
        }
        
        fast = fast.getNext();
        
        if (fast == null) {
            // 只有一个字符
            return true;
        }
        
        while (slow != null) {
            if (!end && (fast = fast.getNext() /* fast走第一步 */) == null) {
                // 偶数个节点，fast到达终点
                back = slow;
                slow = slow.getNext();
                end = true;
            } else if (!end && (fast = fast.getNext() /* fast走第二步 */) == null) {
                // 基数个节点，fast到达终点
                slow = slow.getNext();
                back = slow;
                end = true;
            } else if (!end) {
                slow = slow.getNext(); /* slow走一步 */
            } else {
                if (back.getValue() == slow.getValue() || back.getValue().equals(slow.getValue())) {
                    // slow继续向前走，back向后走，比较slow和back节点值确定是否是回文
                    back = back.getPre(); // 第一个节点的前一个节点是head
                    slow = slow.getNext();   // 最后一个节点的下一个节点是null
                } else {
                    return false;
                }
            }
        }
        
        return true;
    }
}
