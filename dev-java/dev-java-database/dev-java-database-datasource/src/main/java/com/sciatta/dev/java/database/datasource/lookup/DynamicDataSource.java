package com.sciatta.dev.java.database.datasource.lookup;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by yangxiaoyu on 2021/1/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DynamicDataSource
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final String MASTER = "master";
    public static final String SLAVE = "slave";
    public static final ThreadLocal<String> context = new ThreadLocal<>();
    
    @Override
    protected Object determineCurrentLookupKey() {
        return context.get();
    }
}
