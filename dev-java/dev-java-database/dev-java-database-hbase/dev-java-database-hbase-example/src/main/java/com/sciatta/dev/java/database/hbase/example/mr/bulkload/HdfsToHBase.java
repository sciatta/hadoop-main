package com.sciatta.dev.java.database.hbase.example.mr.bulkload;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/2/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HdfsToHBase 利用mr读取数据文件内容，输出为hfile格式的文件
 */
public class HdfsToHBase extends Configured implements Tool {

    static class HdfsReadMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {
        private ImmutableBytesWritable outKey = new ImmutableBytesWritable();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] datas = value.toString().split("\t");
            String rowKey = datas[0];
            String name = datas[1];
            String age = datas[2];

            Put put = new Put(rowKey.getBytes());
            put.addColumn(InitData.DEST_CF.getBytes(), InitData.DEST_C_NAME.getBytes(), name.getBytes());
            put.addColumn(InitData.DEST_CF.getBytes(), InitData.DEST_C_AGE.getBytes(), age.getBytes());

            outKey.set(rowKey.getBytes());

            context.write(outKey, put);
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf());
        job.setJarByClass(HdfsToHBase.class);

        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path(InitData.HDFS_FILE_PATH));

        job.setMapperClass(HdfsReadMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);

        configHFileOutputFormat2(job);
        job.setOutputValueClass(HFileOutputFormat2.class);

        HFileOutputFormat2.setOutputPath(job, new Path(InitData.HDFS_HFILE_PATH));

        return job.waitForCompletion(true) ? 0 : 1;
    }

    private void configHFileOutputFormat2(Job job) throws IOException {
        Connection connection = ConnectionFactory.createConnection(getConf());
        Table table = connection.getTable(TableName.valueOf(InitData.DEST_TABLE));
        HFileOutputFormat2.configureIncrementalLoad(job, table, connection.getRegionLocator(TableName.valueOf(InitData.DEST_TABLE)));

    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set(InitData.ZK_QUORUM_KEY, InitData.ZK_QUORUM_VALUE);
        configuration.set(InitData.TMP_DIR_KEY, InitData.TMP_DIR_VALUE);

        int test = ToolRunner.run(configuration, new HdfsToHBase(), args);
        System.exit(test);
    }
}
