package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * PutFile
 */
public class PutFile extends AbstractOperate {
    @Override
    protected void doWork() throws IOException {
        FileSystem fileSystem = FileSystem.get(getConf());

        // 目的地址如果是文件，则创建文件
        // 目的地址如果是目录，则在目录下创建文件
        // 源地址和目的地址都可以是目录，将源地址下的所有文件上传至目的地址的目录下
        fileSystem.copyFromLocalFile(new Path("/Users/yangxiaoyu/work/test/"), new Path("/test_trash"));

        fileSystem.close();
    }

    public static void main(String[] args) throws IOException {
        PutFile pfo = new PutFile();
        pfo.doWork();
    }
}
