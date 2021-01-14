package com.sciatta.dev.java.database.datasource.lookup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangxiaoyu on 2021/1/12<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CurrentDataSource
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentDataSource {
    DataSourceEnum name() default DataSourceEnum.MASTER;
}
