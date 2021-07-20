package com.sciatta.dev.java.hadoop.hdfs.example.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/9/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HATests<br><p/>
 * 通过NameNode地址访问，优点是只需要修改NameNode地址即可；缺点是故障转移后，还需要知道切换到哪里，从而修改地址才可以访问<br>
 * node01 -> standby 不支持读写 <br>
 * node02 -> active 支持读写 <p/>
 * 通过nameservices配置访问，可以有效解决故障转移的问题
 */
public class HATests {
    private Configuration configuration;

    @Before
    public void init() {
        configuration = new Configuration();
        // namespace基础配置
        configuration.set("dfs.nameservices","ns1");
        configuration.set("dfs.ha.namenodes.ns1","nn1,nn2");
        configuration.set("dfs.namenode.rpc-address.ns1.nn1","node01:8020");
        configuration.set("dfs.namenode.rpc-address.ns1.nn2","node02:8020");
        configuration.set("dfs.client.failover.proxy.provider.ns1","org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");    // 必须配置

        // 需要访问哪一个namespace
        configuration.set("fs.defaultFS", "hdfs://ns1");
    }

    @Test
    public void testPut() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);
        fileSystem.copyFromLocalFile(new Path("/Users/yangxiaoyu/work/test/hdfsdatas/hello"), new Path("/hdfstest/hello"));
        fileSystem.close();
    }

    @Test
    public void testList() throws IOException {
        FileSystem fileSystem = FileSystem.get(configuration);

        RemoteIterator<LocatedFileStatus> remoteIterator = fileSystem.listFiles(new Path("/hdfstest"), true);

        while (remoteIterator.hasNext()) {
            LocatedFileStatus next = remoteIterator.next();
            System.out.println(next.getPath().getName());
        }
    }
}
