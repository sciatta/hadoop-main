package com.sciatta.dev.java.rpc.javaspi;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by yangxiaoyu on 2021/1/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ServiceLoaderTests
 */
public class ServiceLoaderTests {
    @Test
    public void testSearchSpi() {
        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = load.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            System.out.println(Arrays.toString(search.doSearch("hello")));
        }
    }
}
