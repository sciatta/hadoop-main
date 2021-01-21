package com.sciatta.dev.java.algorithm.linear.linked.example;

import com.sciatta.dev.java.algorithm.linear.linked.OneWayLinkedList;

/**
 * Created by yangxiaoyu on 2021/1/21<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MergeTwoSortedLinkedList
 */
public class MergeTwoSortedLinkedList<T extends Comparable<T>> {
    private OneWayLinkedList<T> one = new OneWayLinkedList<>();
    private OneWayLinkedList<T> two = new OneWayLinkedList<>();
    
    public MergeTwoSortedLinkedList(T[] arrayOne, T[] arrayTwo) {
        for (int i = 0; i < arrayOne.length; i++) {
            one.insert(arrayOne[i], i);
        }
        
        for (int i = 0; i < arrayTwo.length; i++) {
            two.insert(arrayTwo[i], i);
        }
    }
    
    public OneWayLinkedList.Node<T> doMerge() {
        OneWayLinkedList.Node<T> oneNode = one.getHead().getNext();
        OneWayLinkedList.Node<T> twoNode = two.getHead().getNext();
        
        // 哨兵节点
        OneWayLinkedList.Node<T> retHead = new OneWayLinkedList.Node<>();
        // 待添加节点的前驱
        OneWayLinkedList.Node<T> pre = retHead;
        
        while (oneNode !=null && twoNode != null) {
            if (oneNode.getValue().compareTo(twoNode.getValue()) <=0) {
                pre.setNext(oneNode);
                pre = oneNode;
                oneNode = oneNode.getNext();
            } else {
                pre.setNext(twoNode);
                pre = twoNode;
                twoNode = twoNode.getNext();
            }
        }
        
        if (oneNode == null) {
            pre.setNext(twoNode);
        } 
        
        if (twoNode == null){
            pre.setNext(oneNode);
        }
        
        return retHead;
    }
}
