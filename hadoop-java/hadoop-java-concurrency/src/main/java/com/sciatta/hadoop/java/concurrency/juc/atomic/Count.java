package com.sciatta.hadoop.java.concurrency.juc.atomic;

/**
 * Created by yangxiaoyu on 2020/11/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Count
 */
public interface Count {
    void addOne();

    int get();
}
