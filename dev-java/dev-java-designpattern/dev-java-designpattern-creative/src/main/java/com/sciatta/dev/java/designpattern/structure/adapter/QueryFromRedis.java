package com.sciatta.dev.java.designpattern.structure.adapter;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * QueryFromRedis
 */
public class QueryFromRedis implements Query {
    private RedisQuery redisQuery;
    
    public QueryFromRedis(RedisQuery redisQuery) {
        this.redisQuery = redisQuery;
    }
    
    @Override
    public String query(String id) {
        return redisQuery.select(id);
    }
}
