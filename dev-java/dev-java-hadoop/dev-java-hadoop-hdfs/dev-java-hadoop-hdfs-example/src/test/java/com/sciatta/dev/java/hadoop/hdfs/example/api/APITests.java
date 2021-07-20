package com.sciatta.dev.java.hadoop.hdfs.example.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/2/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * APITests
 */
public class APITests extends AbstractInit {
    @Test
    public void testMkDir() throws IOException {
        // 创建Configuration，设置参数
        Configuration configuration = this.configuration;

        // 创建FileSystem
        FileSystem fileSystem = FileSystem.get(configuration);

        // 执行命令，dfs api操作的默认用户是当前系统用户
        fileSystem.mkdirs(new Path("/test/hdfs"));

        // 释放资源
        fileSystem.close();
    }

    @Test
    public void testCopyFromLocalFile() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);

        // 目的地址如果是文件，则创建文件
        // 目的地址如果是目录，则在目录下创建文件
        // 源地址和目的地址都可以是目录，将源地址下的所有文件上传至目的地址的目录下
        fileSystem.copyFromLocalFile(new Path("/Users/yangxiaoyu/work/test/hdfsdatas/hello"), new Path("/test/hdfs"));

        fileSystem.close();
    }

    @Test
    public void testPutByStream() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);

        // 输入流
        FileInputStream fis = new FileInputStream(new File("/Users/yangxiaoyu/work/test/hdfsdatas/hello"));
        // 输出流
        // 目录不存在，hdfs自动创建，支持多级目录
        FSDataOutputStream fos = fileSystem.create(new Path("/test/hdfs/put/m/hi"));

        // 流对拷
        IOUtils.copyBytes(fis, fos, BUFFER_SIZE);

        // 释放资源
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);
        fileSystem.close();
    }

    @Test
    public void testCopyToLocalFile() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);

        fileSystem.copyToLocalFile(new Path("/test/hdfs/hello"), new Path("/Users/yangxiaoyu/work/test/hdfsdatas/hi"));
        fileSystem.close();
    }

    @Test
    public void testGetByStream() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);

        FSDataInputStream is = fileSystem.open(new Path("/test/hdfs/hello"));
        FileOutputStream os = new FileOutputStream(new File("/Users/yangxiaoyu/work/test/hdfsdatas/good"));

        IOUtils.copyBytes(is, os, BUFFER_SIZE);
        IOUtils.closeStream(is);
        IOUtils.closeStream(os);
        fileSystem.close();
    }

    @Test
    public void testAppend() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);

        FileInputStream inputStream = new FileInputStream("/Users/yangxiaoyu/work/test/hdfsdatas/bad");
        FSDataOutputStream outputStream = fileSystem.append(new Path("/test/hdfs/hello"));

        IOUtils.copyBytes(inputStream, outputStream, BUFFER_SIZE);

        IOUtils.closeStream(inputStream);
        IOUtils.closeStream(outputStream);
        fileSystem.close();
    }

    @Test
    public void testRename() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);

        // 目的目录不存在不会抛出异常，只会返回false
        boolean rename = fileSystem.rename(new Path("/test/hdfs/hello"), new Path("/test/hdfs/hi"));
        assertTrue(rename);

        fileSystem.close();
    }

    @Test
    public void testListFiles() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);

        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/test/hdfs"), true);

        // 只显示文件，不显示目录
        while (listFiles.hasNext()) {
            LocatedFileStatus status = listFiles.next();

            System.out.println("文件名称     长度    权限    用户    组    路径    块信息");

            StringBuilder sb = new StringBuilder();
            // 获取存储的块信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            for (int j = 0; j < blockLocations.length; j++) {
                if (j != 0) {
                    sb.append(" ");
                }
                // 获取块存储的主机节点
                sb.append("[");
                String[] hosts = blockLocations[j].getHosts();
                for (int i = 0; i < hosts.length; i++) {
                    if (i != 0) {
                        sb.append(" ");
                    }
                    sb.append(hosts[i]);
                }
                sb.append("]");
            }

            System.out.printf("%s    %s    %s    %s    %s    %s    %s%n%n",
                    status.getPath().getName(),
                    status.getLen(),
                    status.getPermission(),
                    status.getOwner(),
                    status.getGroup(),
                    status.getPath(),
                    sb.toString());
        }

        fileSystem.close();
    }

    @Test
    public void testDelete() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);
        // 递归删除目录，及目录下内容
        // 物理删除
        fileSystem.delete(new Path("/test/hdfs/put"), true);
    }

    @Test
    public void testMoveToTrash() throws IOException {
        Trash trash = new Trash(configuration);
        if (trash.isEnabled()) {
            // 删除到当前用户的垃圾桶下
            // /user/CurrentUser/.Trash + 完整路径
            trash.moveToTrash(new Path("/test/hdfs/hi"));
        }
    }

}
