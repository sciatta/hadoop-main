package com.sciatta.dev.bigdata.hadoop.mr.example.phase.order;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SortedFlowMain
 */
public class SortedFlowMain {
    public static void main(String[] args) throws Exception {
        Tool tool;
        if (args.length == 0) {
            tool = new SortedFlowJobLocal();
        } else if (args[0] != null && args[0].equalsIgnoreCase("cluster")) {
            tool = new SortedFlowJobCluster();
        } else if (args[0] != null && args[0].equalsIgnoreCase("local")) {
            tool = new SortedFlowJobLocal();
        } else {
            throw new IllegalArgumentException("local or cluster");
        }


        int test = ToolRunner.run(new Configuration(), tool, args);
        System.exit(test);
    }

}
