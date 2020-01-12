package com.sciatta.hadoop.hdfs.example.api.impl;

import com.sciatta.hadoop.hdfs.example.api.AbstractOperate;
import org.apache.hadoop.fs.*;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ListFiles
 */
public class ListFiles extends AbstractOperate {
    @Override
    protected void doWork() throws IOException {
        FileSystem fileSystem = FileSystem.get(getConf());

        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/main"), true);

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

    public static void main(String[] args) throws IOException {
        ListFiles listFiles = new ListFiles();
        listFiles.doWork();
    }
}
