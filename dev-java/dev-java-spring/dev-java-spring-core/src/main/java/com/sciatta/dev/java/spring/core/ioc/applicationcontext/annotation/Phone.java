package com.sciatta.dev.java.spring.core.ioc.applicationcontext.annotation;

/**
 * Created by yangxiaoyu on 2018/9/18<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Phone
 */
public interface Phone {
    String getName();

    Color getColor();
    
    enum Color {
        RED, BLACK, WHITE, GOLD;
    }
}
