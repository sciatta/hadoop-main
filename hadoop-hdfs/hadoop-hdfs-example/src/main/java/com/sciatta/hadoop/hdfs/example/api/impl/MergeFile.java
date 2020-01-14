package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MergeFile
 */
public class MergeFile extends AbstractOperate {
    @Override
    protected void doWork() throws IOException, URISyntaxException, InterruptedException {
        // hdfs文件系统
        FileSystem fileSystem = FileSystem.get(getConf());
        // local文件系统
        LocalFileSystem localFileSystem = FileSystem.getLocal(new Configuration());

        // hdfs输出流
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/main/merge"));

        // 读取本地文件
        FileStatus[] files = localFileSystem.listStatus(new Path("/Users/yangxiaoyu/work/test"));
        for (FileStatus file : files) {
            Path path = file.getPath();
            System.out.println("file: " + path);
            // local输入流
            FSDataInputStream fsDataInputStream = localFileSystem.open(path);
            // 小文件输出到一个hdfs输出流，追加到末尾
            IOUtils.copyBytes(fsDataInputStream, fsDataOutputStream, BUFFER_SIZE);
            IOUtils.closeStream(fsDataInputStream);
        }

        IOUtils.closeStream(fsDataOutputStream);
        localFileSystem.close();
        fileSystem.close();
    }

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        MergeFile mergeFile = new MergeFile();
        mergeFile.doWork();
    }
}
