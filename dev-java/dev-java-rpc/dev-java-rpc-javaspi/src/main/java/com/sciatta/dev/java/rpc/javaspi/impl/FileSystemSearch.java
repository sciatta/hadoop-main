package com.sciatta.dev.java.rpc.javaspi.impl;

import com.sciatta.dev.java.rpc.javaspi.Search;

/**
 * Created by yangxiaoyu on 2021/1/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FileSystemSearch
 */
public class FileSystemSearch implements Search {
    @Override
    public String[] doSearch(String key) {
        System.out.println("file system search invoke");
        return new String[]{key, "filesystem search"};
    }
}
