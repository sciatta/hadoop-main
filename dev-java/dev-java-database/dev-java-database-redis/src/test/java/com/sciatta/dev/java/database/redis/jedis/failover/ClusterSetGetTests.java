package com.sciatta.dev.java.database.redis.jedis.failover;

import com.sciatta.dev.java.database.redis.jedis.cluster.ClusterSetGet;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yangxiaoyu on 2021/2/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ClusterSetGetTests
 */
public class ClusterSetGetTests {
    private String value;
    
    @Before
    public void init() {
        value = UUID.randomUUID().toString();
    }
    
    @Test
    public void testSetGet() throws IOException {
        ClusterSetGet service = new ClusterSetGet();
        String test = service.set(ClusterSetGet.class.getName(), value);
        assertTrue("ok".equalsIgnoreCase(test));
        
        test = service.get(ClusterSetGet.class.getName());
        assertEquals(value, test);
    }
}
