package com.sciatta.dev.java.database.datasource.lookup.impl;

import com.sciatta.dev.java.database.datasource.lookup.DataSourceEnum;
import com.sciatta.dev.java.database.datasource.lookup.LookupSlave;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangxiaoyu on 2021/1/13<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 轮询
 */
@Service("roundRobin")
public class RoundRobinLookupSlave implements LookupSlave {
    private final AtomicInteger curIndex = new AtomicInteger(0);
    
    @Override
    public DataSourceEnum resolveSlave(List<DataSourceEnum> activeSlaves) {
        int index = curIndex.getAndIncrement() % activeSlaves.size();
        return activeSlaves.get(index);
    }
}
