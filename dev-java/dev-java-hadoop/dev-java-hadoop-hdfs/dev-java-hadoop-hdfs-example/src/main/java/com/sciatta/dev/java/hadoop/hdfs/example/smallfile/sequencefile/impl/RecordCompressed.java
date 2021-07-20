package com.sciatta.dev.java.hadoop.hdfs.example.smallfile.sequencefile.impl;

import com.sciatta.dev.java.hadoop.hdfs.example.smallfile.sequencefile.AbstractSequenceFile;
import org.apache.hadoop.io.SequenceFile;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/23<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RecordCompressed
 */
public class RecordCompressed extends AbstractSequenceFile {
    protected SequenceFile.Writer.Option getCompressed() {
        // Record压缩，不指定压缩算法
        return SequenceFile.Writer.compression(SequenceFile.CompressionType.RECORD);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "record";
        AbstractSequenceFile file = new RecordCompressed();

        file.toHdfs(fileName);

        file.toLocal(fileName);
    }
}
