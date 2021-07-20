package com.sciatta.dev.java.hadoop.hdfs.example.smallfile.localmerge;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MergeFile 通过将客户端多个文件输入流的数据追加到输出流的末尾，从而将若干小文件合并为一个大文件
 */
public class MergeFile {
    private static final String CFG_DEFAULT_FS_K = "fs.defaultFS";
    private static final String CFG_DEFAULT_FS_V = "hdfs://192.168.2.100:8020";

    protected static final int BUFFER_SIZE = 4096;

    private Configuration configuration;

    public MergeFile() {
        configuration = new Configuration();
        configuration.set(CFG_DEFAULT_FS_K, CFG_DEFAULT_FS_V);
    }

    private void doWork() throws IOException {
        // hdfs文件系统
        FileSystem fileSystem = FileSystem.get(configuration);
        // local文件系统
        LocalFileSystem localFileSystem = FileSystem.getLocal(new Configuration());

        // hdfs输出流
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/test/hdfs/merge"));

        // 读取本地文件
        FileStatus[] files = localFileSystem.listStatus(new Path("/Users/yangxiaoyu/work/test/hdfsdatas/merge"));
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
