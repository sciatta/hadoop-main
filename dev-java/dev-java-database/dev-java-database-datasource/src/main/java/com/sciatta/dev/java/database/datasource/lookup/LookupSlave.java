package com.sciatta.dev.java.database.datasource.lookup;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/1/13<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LockupSlave
 */
public interface LookupSlave {
    DataSourceEnum resolveSlave(List<DataSourceEnum> activeSlaves);
}
