package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Rename
 */
public class Rename extends AbstractOperate {
    @Override
    protected void doWork() throws IOException {
        FileSystem fileSystem = FileSystem.get(getConf());

        // 目录不存在不会报错
        fileSystem.rename(new Path("/main/hello"), new Path("/main/hi"));

        fileSystem.close();
    }

    public static void main(String[] args) throws IOException {
        Rename rename = new Rename();
        rename.doWork();
    }
}
