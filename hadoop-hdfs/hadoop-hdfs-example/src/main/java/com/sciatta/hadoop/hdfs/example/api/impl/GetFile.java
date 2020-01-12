package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * GetFile
 */
public class GetFile extends AbstractOperate {
    @Override
    protected void doWork() throws IOException {
        FileSystem fileSystem = FileSystem.get(getConf());

        fileSystem.copyToLocalFile(new Path("/main/hello"), new Path("/Users/yangxiaoyu/work/test/hi"));
        fileSystem.close();
    }

    public static void main(String[] args) throws IOException {
        GetFile getFile = new GetFile();
        getFile.doWork();
    }
}
