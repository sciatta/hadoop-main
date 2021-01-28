package com.sciatta.dev.java.algorithm.linear.stack.impl;

import java.util.Arrays;

/**
 * Created by yangxiaoyu on 2021/1/28<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 基于数组的栈，可动态扩容
 */
public class ArrayStack<T> {
    private T[] datas;
    private int length;
    private int count;
    
    @SuppressWarnings("unchecked")
    public ArrayStack(int length) {
        this.length = length;
        datas = (T[]) new Object[length];
        count = 0;
    }
    
    public void push(T data) {
        if (count >= length) {
            // 扩容
            expand();
        }
        datas[count++] = data;
    }
    
    public T pop() {
        if (count == 0) return null;
        
        return datas[--count];
    }
    
    public T peek() {
        if (count == 0) return null;
        
        return datas[count - 1];
    }
    
    private void expand() {
        length = length * 2;
        datas = Arrays.copyOf(datas, length);
    }
    
    public int getLength() {
        return length;
    }
    
    public int getCount() {
        return count;
    }
}
