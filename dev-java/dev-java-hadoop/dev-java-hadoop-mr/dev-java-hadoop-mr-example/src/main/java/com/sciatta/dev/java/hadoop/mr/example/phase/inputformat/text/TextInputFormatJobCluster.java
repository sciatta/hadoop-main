package com.sciatta.dev.java.hadoop.mr.example.phase.inputformat.text;

import com.sciatta.dev.java.hadoop.mr.example.AbstractJobRunner;
import org.apache.hadoop.conf.Configuration;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * TextInputFormatJobCluster
 */
public class TextInputFormatJobCluster extends TextInputFormatJobLocal {
    @Override
    protected Class<?> getJobClass() {
        return TextInputFormatJobCluster.class;
    }

    @Override
    protected String getInputPath() {
        return AbstractJobRunner.CLUSTER_PARENT_PATH + INPUT_FILE_POSITION;
    }

    @Override
    protected String getOutputPath() {
        return AbstractJobRunner.CLUSTER_PARENT_PATH + OUTPUT_FILE_POSITION;
    }

    @Override
    protected void initConfig(Configuration configuration) {
        // map阶段压缩
        configuration.set("mapreduce.map.output.compress", "true");
        configuration.set("mapreduce.map.output.compress.codec", "org.apache.hadoop.io.compress.SnappyCodec");

        // reduce阶段压缩
        configuration.set("mapreduce.output.fileoutputformat.compress", "true");
        configuration.set("mapreduce.output.fileoutputformat.compress.type", "RECORD");
        configuration.set("mapreduce.output.fileoutputformat.compress.codec", "org.apache.hadoop.io.compress.SnappyCodec"); // snappy压缩

        // 当前用户和用户组是 hadoop hadoop
        // 不设置队列时，使用当前系统用户和用户组提交队列，提交至队列root.hadoop

        // 不可提交至队列root.develop(也可以简写成develop) ，因为其限制用户和用户组是develop develop
        // configuration.set("mapred.job.queue.name", "develop");

        // 当前用户和用户组是develop develop
        // 可提交至队列root.develop
        // configuration.set("mapred.job.queue.name", "develop");
    }
}
