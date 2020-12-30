package com.sciatta.dev.java.example.object.nestedclass;

import java.util.Iterator;

/**
 * Created by yangxiaoyu on 2019/1/20<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * InnerClass
 */
public class InnerClass {
    private static final int length = 10;
    private int[] array = new int[length];

    public InnerClass() {
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
    }

    // 1、内部类作为外部类的成员，因此，成员的访问权限都适用；2、内部类一般用作帮助类
    private class ArrayIterator implements Iterator {
        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < length;   // 可以访问外部类的变量
        }

        @Override
        public Object next() {
            return array[currentIndex++];
        }
    }

    public void print() {
        // 实例化内部类，object.new InnerClass(...)，其中object是外部类的实例
        // Iterator iterator = this.new ArrayIterator();

        // 在外部类的方法中使用内部类，可以省略隐含的this
        Iterator iterator = new ArrayIterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}
