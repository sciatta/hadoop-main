package com.sciatta.hadoop.hdfs.example.rpc;

import org.apache.hadoop.ipc.ProtocolInfo;

/**
 * Created by yangxiaoyu on 2020/7/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Dog 协议接口，必须要有协议版本
 */
@ProtocolInfo(protocolName = "Dog", protocolVersion = 1)
public interface Dog {
    boolean eat(String sth);

    void run();
}
