package com.sciatta.dev.java.algorithm.linear.linked.resolve;

import com.sciatta.dev.java.algorithm.linear.linked.impl.OneWayLinkedList;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/1/21<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MergeTwoSortedLinkedListTests
 */
public class MergeTwoSortedLinkedListTests {
    @Test
    public void testMerge() {
        MergeTwoSortedLinkedList<Integer> list = new MergeTwoSortedLinkedList<>(new Integer[]{1, 6}
                , new Integer[]{2, 5, 10, 18});
        
        OneWayLinkedList.Node<Integer> retNode = list.doMerge();
        assertArrayEquals(new Integer[]{1, 2, 5, 6, 10, 18}, getArray(retNode));
    }
    
    @Test
    public void testAllEmpty() {
        MergeTwoSortedLinkedList<Integer> list = new MergeTwoSortedLinkedList<>(new Integer[]{}
                , new Integer[]{});
        
        OneWayLinkedList.Node<Integer> retNode = list.doMerge();
        assertArrayEquals(new Integer[]{}, getArray(retNode));
    }
    
    @Test
    public void testLeftEmpty() {
        MergeTwoSortedLinkedList<Integer> list = new MergeTwoSortedLinkedList<>(new Integer[]{1, 2, 3}
                , new Integer[]{});
        
        OneWayLinkedList.Node<Integer> retNode = list.doMerge();
        assertArrayEquals(new Integer[]{1, 2, 3}, getArray(retNode));
    }
    
    @Test
    public void testRightEmpty() {
        MergeTwoSortedLinkedList<Integer> list = new MergeTwoSortedLinkedList<>(new Integer[]{}
                , new Integer[]{1, 2, 3});
        
        OneWayLinkedList.Node<Integer> retNode = list.doMerge();
        assertArrayEquals(new Integer[]{1, 2, 3}, getArray(retNode));
    }
    
    private Object[] getArray(OneWayLinkedList.Node<Integer> retNode) {
        OneWayLinkedList<Integer> helpList = new OneWayLinkedList<>();
        
        OneWayLinkedList.Node<Integer> curNode = retNode.getNext();
        int index = 0;
        while (curNode != null) {
            helpList.insert(curNode.getValue(), index++);
            curNode = curNode.getNext();
        }
        
        return helpList.toArray();
    }
}
