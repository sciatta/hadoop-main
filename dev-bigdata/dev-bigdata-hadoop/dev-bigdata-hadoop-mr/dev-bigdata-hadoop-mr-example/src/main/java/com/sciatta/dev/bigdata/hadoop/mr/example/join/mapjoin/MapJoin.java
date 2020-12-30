package com.sciatta.dev.bigdata.hadoop.mr.example.join.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/31<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MapJoin
 */
public class MapJoin {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new MapJoinJob(), args);
        System.exit(test);
    }
}
