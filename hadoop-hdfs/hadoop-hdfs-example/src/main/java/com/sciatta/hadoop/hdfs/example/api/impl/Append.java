package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by yangxiaoyu on 2020/1/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Append
 */
public class Append extends AbstractOperate {
    @Override
    protected void doWork() throws IOException, URISyntaxException, InterruptedException {
        FileSystem fileSystem = FileSystem.get(getConf());

        FileInputStream inputStream = new FileInputStream("/Users/yangxiaoyu/work/test/2");
        FSDataOutputStream outputStream = fileSystem.append(new Path("/main/data/1"));

        IOUtils.copyBytes(inputStream, outputStream, BUFFER_SIZE);

        IOUtils.closeStream(inputStream);
        IOUtils.closeStream(outputStream);
        fileSystem.close();
    }

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        Append append = new Append();
        append.doWork();
    }
}
