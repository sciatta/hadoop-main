package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Mkdir
 */
public class Mkdir extends AbstractOperate {


    public static void main(String[] args) throws IOException {
        Mkdir operate = new Mkdir();

        operate.doWork();
    }

    @Override
    protected void doWork() throws IOException {
        // 创建Configuration，设置参数
        Configuration configuration = getConf();

        // 创建FileSystem
        FileSystem fileSystem = FileSystem.get(configuration);

        // 执行命令，dfs api操作的默认用户是当前系统用户
        fileSystem.mkdirs(new Path("/main"));

        // 释放资源
        fileSystem.close();
    }
}
