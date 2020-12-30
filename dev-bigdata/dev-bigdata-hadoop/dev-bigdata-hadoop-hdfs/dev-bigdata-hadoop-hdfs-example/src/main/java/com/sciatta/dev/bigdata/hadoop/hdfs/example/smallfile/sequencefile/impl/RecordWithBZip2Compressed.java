package com.sciatta.dev.bigdata.hadoop.hdfs.example.smallfile.sequencefile.impl;

import com.sciatta.dev.bigdata.hadoop.hdfs.example.smallfile.sequencefile.AbstractSequenceFile;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.compress.BZip2Codec;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/24<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RecordWithBZip2Compressed
 */
public class RecordWithBZip2Compressed extends AbstractSequenceFile {
    protected SequenceFile.Writer.Option getCompressed() {
        BZip2Codec codec = new BZip2Codec();
        codec.setConf(configuration);

        return SequenceFile.Writer.compression(SequenceFile.CompressionType.RECORD, codec);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "recordwithbzip2";
        AbstractSequenceFile file = new RecordWithBZip2Compressed();

        file.toHdfs(fileName);

        file.toLocal(fileName);
    }
}
