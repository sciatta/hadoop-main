package com.sciatta.dev.bigdata.hbase.example.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;


/**
 * Created by yangxiaoyu on 2020/2/20<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HdfsToHBase
 * 将hdfs数据导入到HBase表，需要访问HRegionServer
 */
public class HdfsToHBase extends Configured implements Tool {
    private static final String ZK_QUORUM_KEY = "hbase.zookeeper.quorum";
    private static final String ZK_QUORUM_VALUE = "node01:2181,node02:2181,node03:2181";

    private static final String DEST_TABLE = "user1";
    private static final String DEST_CF = "cf";
    private static final String DEST_C_NAME = "name";
    private static final String DEST_C_AGE = "age";

    private static final String HDFS_FILE_PATH = "hdfs://node01:8020/test/hbase/user";

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf());
        job.setJarByClass(HdfsToHBase.class);

        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path(HDFS_FILE_PATH));

        job.setMapperClass(HdfsReadMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        TableMapReduceUtil.initTableReducerJob(DEST_TABLE, HBaseWriteReducer.class, job);

        job.setNumReduceTasks(2);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    static class HdfsReadMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            context.write(value, NullWritable.get());
        }
    }

    static class HBaseWriteReducer extends TableReducer<Text, NullWritable, ImmutableBytesWritable> {
        private ImmutableBytesWritable outKey = new ImmutableBytesWritable();

        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            String[] datas = key.toString().split("\t");
            String rowKey = datas[0];
            String name = datas[1];
            String age = datas[2];

            Put put = new Put(rowKey.getBytes());
            put.addColumn(DEST_CF.getBytes(), DEST_C_NAME.getBytes(), name.getBytes());
            put.addColumn(DEST_CF.getBytes(), DEST_C_AGE.getBytes(), age.getBytes());

            outKey.set(rowKey.getBytes());

            context.write(outKey, put);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set(ZK_QUORUM_KEY, ZK_QUORUM_VALUE);

        int test = ToolRunner.run(configuration, new HdfsToHBase(), args);
        System.exit(test);
    }
}
