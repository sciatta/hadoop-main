package com.sciatta.dev.java.designpattern.structure.bridge;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * HdfsStore
 */
public class HdfsStore implements Store {
    private ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    
    private HdfsStore() {
    
    }
    
    static {
        StoreManager.register(new HdfsStore());
    }
    
    @Override
    public String put(String localPath) {
        String id = UUID.randomUUID().toString();
        cache.put(id, localPath);
        
        System.out.println("put " + localPath + " to hdfs store, return id " + id);
        
        return id;
    }
    
    @Override
    public String get(String id) {
        String path = cache.get(id);
        System.out.println("get " + id + " from hdfs store, return path " + path);
        return path;
    }
}
