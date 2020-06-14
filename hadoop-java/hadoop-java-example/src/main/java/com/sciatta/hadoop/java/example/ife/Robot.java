package com.sciatta.hadoop.java.example.ife;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by yangxiaoyu on 2018/4/15<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Robot
 */
public interface Robot {
    default void sleep() {
        System.out.println("Don't sleep.");
    }

    default String create() {
        // lambda 表达式会回调执行（本质上只是函数作为参数传递，并不执行）
        return process(() -> getStr(), in -> in + " creating");
    }

    default String getStr() {
        return "interface";
    }

    default String process(Supplier<String> in, Function<String, String> out) {
        return out.apply(in.get() + " and parent process");
    }
}
