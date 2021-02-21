package com.sciatta.dev.java.database.redis.jedis.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yangxiaoyu on 2021/2/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ClusterSetGet
 */
public class ClusterSetGet {
    private final JedisCluster cluster;
    
    public ClusterSetGet() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("172.82.0.70", 7000));
        nodes.add(new HostAndPort("172.82.0.71", 7000));
        nodes.add(new HostAndPort("172.82.0.72", 7000));
        nodes.add(new HostAndPort("172.82.0.73", 7000));
        nodes.add(new HostAndPort("172.82.0.74", 7000));
        nodes.add(new HostAndPort("172.82.0.75", 7000));
        
        cluster = new JedisCluster(nodes);
    }
    
    public String set(String key, String value) throws IOException {
        String result = cluster.set(key, value);
        return result;
    }
    
    public String get(String key) throws IOException {
        String result = cluster.get(key);
        return result;
    }
}
