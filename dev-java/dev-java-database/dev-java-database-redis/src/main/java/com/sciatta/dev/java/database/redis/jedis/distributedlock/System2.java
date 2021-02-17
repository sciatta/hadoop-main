package com.sciatta.dev.java.database.redis.jedis.distributedlock;

import java.util.UUID;

/**
 * Created by yangxiaoyu on 2021/2/17<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * System2
 */
public class System2 extends AbstractSystem {
    public System2(String requestId, int taskExecutionTimes, int taskExecutionSeconds) {
        super(requestId, taskExecutionTimes, taskExecutionSeconds);
    }
    
    public static void main(String[] args) {
        System2 system = new System2(UUID.randomUUID().toString(), 3, 5);
        system.run();
    }
}
