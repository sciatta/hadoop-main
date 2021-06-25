package com.sciatta.dev.java.designpattern.structure.adapter;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DataBaseQuery
 */
public class DataBaseQuery {
    public String query(String id) {
        System.out.println("query from database...");
        return id;
    }
}
