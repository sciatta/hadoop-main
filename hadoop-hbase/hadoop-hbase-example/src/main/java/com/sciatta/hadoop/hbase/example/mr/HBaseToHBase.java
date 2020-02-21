package com.sciatta.hadoop.hbase.example.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/2/20<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HBaseReadMapper
 * 将user表info列族下的name和age数据，插入到user1表的cf列族下
 */
public class HBaseToHBase extends Configured implements Tool {

    private static final String ZK_QUORUM_KEY = "hbase.zookeeper.quorum";
    private static final String ZK_QUORUM_VALUE = "node01:2181,node02:2181,node03:2181";

    private static final String SOURCE_TABLE = "user";
    private static final String SOURCE_CF = "info";
    private static final String SOURCE_C_NAME = "name";
    private static final String SOURCE_C_AGE = "age";

    private static final String DEST_TABLE = "user1";
    private static final String DEST_CF = "cf";

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf());
        job.setJarByClass(HBaseToHBase.class);

        TableMapReduceUtil.initTableMapperJob(TableName.valueOf(SOURCE_TABLE), new Scan(), HBaseReadMapper.class,
                ImmutableBytesWritable.class, Put.class, job);

        TableMapReduceUtil.initTableReducerJob(DEST_TABLE, HBaseWriteReducer.class, job);

        boolean b = job.waitForCompletion(true);
        return b ? 0 : 1;
    }

    static class HBaseReadMapper extends TableMapper<ImmutableBytesWritable, Put> {
        @Override
        protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
            Cell[] cells = value.rawCells();

            // 待转换一行数据
            Put put = new Put(key.get());

            for (Cell cell : cells) {
                String cf = Bytes.toString(CellUtil.cloneFamily(cell));
                if (SOURCE_CF.equals(cf)) {
                    String cq = Bytes.toString(CellUtil.cloneQualifier(cell));
                    if (SOURCE_C_NAME.equals(cq) || SOURCE_C_AGE.equals(cq)) {
                        put.addColumn(DEST_CF.getBytes(), cq.getBytes(), CellUtil.cloneValue(cell));
                    }
                }
            }

            if (!put.isEmpty()) {
                context.write(key, put);
            }
        }
    }

    static class HBaseWriteReducer extends TableReducer<ImmutableBytesWritable, Put, ImmutableBytesWritable> {
        @Override
        protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
            for (Put put : values) {
                context.write(key, put);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set(ZK_QUORUM_KEY, ZK_QUORUM_VALUE);

        int test = ToolRunner.run(configuration, new HBaseToHBase(), args);
        System.exit(test);
    }
}
