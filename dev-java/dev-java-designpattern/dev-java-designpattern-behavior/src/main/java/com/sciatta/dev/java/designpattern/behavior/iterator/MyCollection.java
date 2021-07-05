package com.sciatta.dev.java.designpattern.behavior.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/5<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MyCollection
 */
public class MyCollection<T> implements Iterable<T> {
    
    private final List<T> list = new ArrayList<>();
    
    public void addData(T data) {
        list.add(data);
    }
    
    private class MyIterator implements Iterator<T> {
        private final Iterator<T> ite;
        
        public MyIterator(Iterator<T> ite) {
            this.ite = ite;
        }
        
        @Override
        public boolean hasNext() {
            return ite.hasNext();
        }
        
        @Override
        public T next() {
            return ite.next();
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new MyIterator(list.iterator());
    }
}
