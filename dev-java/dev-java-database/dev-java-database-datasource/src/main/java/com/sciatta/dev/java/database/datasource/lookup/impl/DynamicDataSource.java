package com.sciatta.dev.java.database.datasource.lookup.impl;

import com.sciatta.dev.java.database.datasource.lookup.DataSourceEnum;
import com.sciatta.dev.java.database.datasource.lookup.LookupSlave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/1/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DynamicDataSource
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    private static final ThreadLocal<DataSourceEnum> context = new ThreadLocal<>();
    private final List<DataSourceEnum> activeSlaves = new ArrayList<>();
    
    @Autowired
    @Qualifier("roundRobin")
    private LookupSlave lookupSlave;
    
    @Override
    protected Object determineCurrentLookupKey() {
        if (context.get().equals(DataSourceEnum.SLAVE)) {
            // 多个slave，负载均衡
            DataSourceEnum slave = lookupSlave.resolveSlave(activeSlaves);
            // 重新设置访问的slave
            context.set(slave);
        }
        
        return context.get();
    }
    
    public static void setDataSourceKey(DataSourceEnum key) {
        context.set(key);
    }
    
    public static void resetDataSourceKey() {
        context.remove();
    }
    
    public void setSlaveKey(DataSourceEnum dse) {
        activeSlaves.add(dse);
    }
}
