package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * PutFileByStream
 */
public class PutFileByStream extends AbstractOperate {
    @Override
    protected void doWork() throws IOException {
        FileSystem fileSystem = FileSystem.get(getConf());

        // 输入流
        FileInputStream fis = new FileInputStream(new File("/Users/yangxiaoyu/work/test/hello"));
        // 输出流
        FSDataOutputStream fos = fileSystem.create(new Path("/main/hi"));    // 目录不存在，hdfs自动创建

        // 流对拷
        IOUtils.copyBytes(fis, fos, BUFFER_SIZE);

        // 释放资源
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);
        fileSystem.close();
    }

    public static void main(String[] args) throws IOException {
        PutFileByStream putFileByStream = new PutFileByStream();
        putFileByStream.doWork();
    }
}
