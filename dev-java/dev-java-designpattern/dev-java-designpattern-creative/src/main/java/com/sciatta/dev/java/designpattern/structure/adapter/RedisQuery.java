package com.sciatta.dev.java.designpattern.structure.adapter;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * RedisQuery
 */
public class RedisQuery {
    public String select(String id) {
        System.out.println("from redis system...");
        return id;
    }
}
