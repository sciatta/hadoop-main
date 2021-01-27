package com.sciatta.dev.java.algorithm.linear.linked.resolve;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/1/26<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CheckForRingTests
 */
public class CheckForRingTests {
    @Test
    public void testHasRing() {
        CheckForRing checkForRing = new CheckForRing(buildHasRingList());
        assertTrue(checkForRing.hasRing());
        
        // 相遇节点
        assertEquals(6, checkForRing.getMeet().value.intValue());
        
        // 环长度
        assertEquals(5, checkForRing.getRingLength());
        
        // 环入口
        assertEquals(3, checkForRing.getEntryOfRing().value.intValue());
    }
    
    @Test
    public void testNotHasRing() {
        CheckForRing checkForRing = new CheckForRing(buildNotHasRingList());
        assertFalse(checkForRing.hasRing());
        
        // 环长度
        assertEquals(0, checkForRing.getRingLength());
        
        // 环入口
        assertNull(checkForRing.getEntryOfRing());
    }
    
    private CheckForRing.Node buildHasRingList() {
        CheckForRing.Node head = null, tail = null, meet = null;
        
        for (int i = 1; i <= 7; i++) {
            if (i == 1) {
                head = tail = newNode(i);
            } else {
                tail.next = newNode(i);
                tail = tail.next;
                
                if (i == 3) {
                    meet = tail;    // 入环节点
                }
                
                if (i == 7) {
                    tail.next = meet;    //  最后一个节点连接入环节点
                }
            }
        }
        
        return head;
    }
    
    private CheckForRing.Node buildNotHasRingList() {
        CheckForRing.Node head = null, tail = null;
        for (int i = 1; i <= 7; i++) {
            if (i == 1) {
                head = tail = newNode(i);
            } else {
                tail.next = newNode(i);
                tail = tail.next;
            }
        }
        return head;
    }
    
    private CheckForRing.Node newNode(Integer v) {
        CheckForRing.Node node = new CheckForRing.Node();
        node.value = v;
        return node;
    }
}
