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
        fileSystem.copyFromLocalFile(new Path("/Users/yangxiaoyu/work/test/hello"), new Path("/main/hello"));

        fileSystem.close();
    }

    public static void main(String[] args) throws IOException {
        PutFile pfo = new PutFile();
        pfo.doWork();
    }
}
