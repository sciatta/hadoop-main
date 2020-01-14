package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Delete 物理删除，不进回收站
 */
public class Delete extends AbstractOperate {
    @Override
    protected void doWork() throws IOException {
        FileSystem fileSystem = FileSystem.get(getConf());

        fileSystem.delete(new Path("/test_trash"), true); // 递归删除目录，及目录下内容
    }

    public static void main(String[] args) throws IOException {
        Delete delete = new Delete();
        delete.doWork();
    }
}
