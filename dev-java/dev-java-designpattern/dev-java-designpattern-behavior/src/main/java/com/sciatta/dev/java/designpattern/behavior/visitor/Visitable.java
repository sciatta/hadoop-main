package com.sciatta.dev.java.designpattern.behavior.visitor;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Visitable
 */
public interface Visitable {
    void accept(Visitor visitor);
}
