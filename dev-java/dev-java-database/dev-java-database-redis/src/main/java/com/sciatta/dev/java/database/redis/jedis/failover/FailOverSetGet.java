package com.sciatta.dev.java.database.redis.jedis.failover;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yangxiaoyu on 2021/2/18<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FailOverUtil
 */
public class FailOverSetGet {
    private JedisSentinelPool pool;
    
    public FailOverSetGet() {
        Set<String> sentinels = new HashSet<>();
        sentinels.add(new HostAndPort("172.82.0.200", 26379).toString());
        sentinels.add(new HostAndPort("172.82.0.201", 26379).toString());
        sentinels.add(new HostAndPort("172.82.0.202", 26379).toString());
        pool = new JedisSentinelPool("mymaster", sentinels);
    }
    
    public String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        
        try {
            jedis = pool.getResource();
            result = jedis.set(key, value);
        } finally {
            if (jedis != null) jedis.close();
        }
        
        return result;
    }
    
    public String get (String key) {
        Jedis jedis = null;
        String result = null;
        
        try {
            jedis = pool.getResource();
            result = jedis.get(key);
        } finally {
            jedis.close();
        }
        
        return result;
    }
}
