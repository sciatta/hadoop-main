package com.sciatta.dev.java.example.generics;

/**
 * Created by yangxiaoyu on 2019-02-18<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * Box 泛型类型实例
 */
public class Box<T extends Number> {   // T 为类型参数
    T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    // 只接受Box<Number>类型，或其子类型（子类型的参数化类型也必须是Number）
    public static void testBox(Box<Number> box) {
    }

    public static void testAnyBox(Box<?> box) {
    }
}
