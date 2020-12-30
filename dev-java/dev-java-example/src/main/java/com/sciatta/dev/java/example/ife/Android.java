package com.sciatta.dev.java.example.ife;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by yangxiaoyu on 2018/4/17<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Android
 */
public class Android implements Robot {

    public Android() {
        String inner = create();    // 父类默认方法
        System.out.println(inner);
    }

    @Override
    public String process(Supplier<String> in, Function<String, String> out) {  // 覆盖父类方法
        return out.apply(in.get() + " and child process");
    }
}
