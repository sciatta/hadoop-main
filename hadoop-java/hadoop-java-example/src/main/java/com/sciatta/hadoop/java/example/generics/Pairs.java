package com.sciatta.hadoop.java.example.generics;

/**
 * Created by yangxiaoyu on 2019-02-18<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * Pairs 泛型方法实例
 */
public class Pairs<K, V extends Number> {
    private K key;
    private V value;

    public Pairs(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // 静态方法泛型，<A, B>要在返回类型前
    public static <A, B extends Number> boolean equals(Pairs<A, B> one, Pairs<A, B> other) {
        return one.key.equals(other.key) && one.value.equals(other.value);
    }

    // 非静态方法泛型
    public <A, B extends Number> boolean valueLess(Pairs<A, B> p) {
        return this.value.intValue() < p.value.intValue();
    }
}