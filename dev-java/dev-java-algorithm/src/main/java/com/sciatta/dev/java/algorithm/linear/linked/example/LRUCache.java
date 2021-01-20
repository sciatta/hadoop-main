package com.sciatta.dev.java.algorithm.linear.linked.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangxiaoyu on 2021/1/18<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 最近最少使用
 */
public class LRUCache<K, V> {
    private final LinkedList<Node<K, V>> cache = new LinkedList<>();
    private final int capacity;
    
    static class Node<K, V> {
        K key;
        V value;
        
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public V get(K key) {
        for (Node<K, V> test : cache) {
            if (test.key == key || test.key.equals(key)) {
                // 命中，移到链表头，链表头是最常使用的
                boolean remove = cache.remove(test);
                if (remove) {
                    cache.addFirst(test);
                }
                return test.value;
            }
        }
        return null;
    }
    
    public void put(K key, V value) {
        if (cache.size() == capacity) {
            // 缓存已满，移除链表尾，链表尾是最不常使用的
            cache.removeLast();
        }
        
        // 缓存未满，插入链表头
        cache.addFirst(new Node<>(key, value));
    }
    
    public K[] keySnapshot() {
        return (K[]) cache.stream().map(kvNode -> kvNode.key).toArray();
    }
    
    public V[] valueSnapshot() {
        return (V[]) cache.stream().map(kvNode -> kvNode.value).toArray();
    }
}
