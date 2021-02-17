package com.sciatta.dev.java.database.redis.jedis.distributedlock;

import java.util.UUID;

/**
 * Created by yangxiaoyu on 2021/2/16<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * System2
 */
public class System1 extends AbstractSystem {
    
    public System1(String requestId, int taskExecutionTimes, int taskExecutionSeconds) {
        super(requestId, taskExecutionTimes, taskExecutionSeconds);
    }
    
    public static void main(String[] args) {
        System1 system = new System1(UUID.randomUUID().toString(), 5, 10);
        system.run();
    }
}
