package com.sciatta.dev.java.designpattern.structure.adapter;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * QueryFromDataBase
 */
public class QueryFromDataBase implements Query {
    private DataBaseQuery dataBaseQuery;
    
    public QueryFromDataBase(DataBaseQuery dataBaseQuery) {
        this.dataBaseQuery = dataBaseQuery;
    }
    
    @Override
    public String query(String id) {
        return dataBaseQuery.query(id);
    }
}
