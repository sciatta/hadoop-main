package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.Trash;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by yangxiaoyu on 2020/1/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * DeleteToTrash 删除后进回收站
 */
public class DeleteToTrash extends AbstractOperate {
    @Override
    protected void doWork() throws IOException, URISyntaxException, InterruptedException {
        Trash trash = new Trash(getConf());
        if (trash.isEnabled()) {
            // 删除到当前用户的垃圾桶下
            // /user/CurrentUser/.Trash
            trash.moveToTrash(new Path("/test_trash"));
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        DeleteToTrash toTrash = new DeleteToTrash();
        toTrash.doWork();
    }
}
