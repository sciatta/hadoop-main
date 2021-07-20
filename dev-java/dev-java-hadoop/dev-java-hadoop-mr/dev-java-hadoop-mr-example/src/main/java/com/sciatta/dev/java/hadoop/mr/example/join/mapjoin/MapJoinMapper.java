package com.sciatta.dev.java.hadoop.mr.example.join.mapjoin;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2020/1/31<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MapJoinMapper
 */
public class MapJoinMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    private Map<String, String> products = new HashMap<>();
    private Text outputKey = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        String line;

        // task运行后，仅调用一次
        // 读入缓存文件
        URI[] files = context.getCacheFiles();
        FSDataInputStream inputStream = FileSystem.get(context.getConfiguration()).open(new Path(files[0]));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = reader.readLine()) != null) {
            String[] field = line.split(",");
            products.put(field[0], line);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 仅解析订单文件
        String line = value.toString();
        String[] field = line.split(",");
        // 订单
        String productId = field[2];
        outputKey.set(line + "," + products.get(productId));

        context.write(outputKey, NullWritable.get());
    }
}
