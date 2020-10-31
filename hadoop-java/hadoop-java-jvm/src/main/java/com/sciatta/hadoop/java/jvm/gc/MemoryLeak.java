package com.sciatta.hadoop.java.jvm.gc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yangxiaoyu on 2020/10/31<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 内存泄露示例
 */
public class MemoryLeak {
    static class Key {
        Integer id;

        public Key(Integer id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(id, key.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static void main(String[] args) {
        Map<Key, Integer> m = new HashMap<>();

        int num = 0;

        m.put(new Key(num), num);

        // 此处会导致内存泄露，默认比较两个对象是否相同，需要重写hashCode和equals方法
        // 内存泄露：引用了本该回收的无意义对象
        if (!m.containsKey(new Key(num))) {
            m.put(new Key(num), num);
        }

        System.out.println("共有 " + m.size() + " 个实例");
        System.out.println(m);
    }
}
