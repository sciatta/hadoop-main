package com.sciatta.dev.java.algorithm.linear.linked.resolve;

/**
 * Created by yangxiaoyu on 2021/1/26<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CheckForRing
 */
public class CheckForRing {
    private Node head;  // 头节点
    private Node meet;  // 相遇节点
    
    static class Node {
        Integer value;
        Node next;
    }
    
    public CheckForRing(Node checkList) {
        head = checkList;
    }
    
    public Node getHead() {
        return head;
    }
    
    public Node getMeet() {
        return meet;
    }
    
    // 链表是否有环
    public boolean hasRing() {
        if (head == null) return false;
        
        // 指向第一个节点
        Node slow = head;
        Node fast = slow;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;   // 慢指针走一步
            fast = fast.next.next;  // 快指针走两步
            
            if (fast.value.equals(slow.value)) {
                // 相遇
                meet = fast;
                return true;
            }
        }
        
        return false;
    }
    
    // 环的长度
    public int getRingLength() {
        int length = 0;
        
        if (hasRing()) {
            Node slow = meet;
            
            while (slow.next != meet) {
                slow = slow.next;
                length++;
            }
            ++length;  // 累加最后一次
        }
        
        return length;
    }
    
    // 环的入口节点
    public Node getEntryOfRing() {
        Node entry = null;
        
        if (hasRing()) {
            Node slow = meet;
            Node start = head;
            
            while (slow != start) {
                slow = slow.next;
                start = start.next;
            }
            
            entry = start;
        }
        
        return entry;
    }
}
