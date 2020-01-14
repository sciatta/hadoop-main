package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * GetFileByStream
 */
public class GetFileByStream extends AbstractOperate {
    @Override
    protected void doWork() throws IOException {
        FileSystem fileSystem = FileSystem.get(getConf());

        FSDataInputStream is = fileSystem.open(new Path("/main/hello"));
        FileOutputStream os = new FileOutputStream(new File("/Users/yangxiaoyu/work/test/nicetomeetyou"));

        IOUtils.copyBytes(is, os, BUFFER_SIZE);
        IOUtils.closeStream(is);
        IOUtils.closeStream(os);
        fileSystem.close();
    }

    public static void main(String[] args) throws IOException {
        GetFileByStream getFileByStream = new GetFileByStream();
        getFileByStream.doWork();
    }
}
