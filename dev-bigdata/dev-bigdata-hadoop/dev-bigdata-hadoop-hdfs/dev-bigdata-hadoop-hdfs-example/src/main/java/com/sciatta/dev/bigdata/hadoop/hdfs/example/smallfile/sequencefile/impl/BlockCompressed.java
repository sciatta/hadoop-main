package com.sciatta.dev.bigdata.hadoop.hdfs.example.smallfile.sequencefile.impl;

import com.sciatta.dev.bigdata.hadoop.hdfs.example.smallfile.sequencefile.AbstractSequenceFile;
import org.apache.hadoop.io.SequenceFile;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/23<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * BlockCompressed
 */
public class BlockCompressed extends AbstractSequenceFile {
    protected SequenceFile.Writer.Option getCompressed() {
        return SequenceFile.Writer.compression(SequenceFile.CompressionType.BLOCK);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "block";
        AbstractSequenceFile file = new BlockCompressed();

        file.toHdfs(fileName);

        file.toLocal(fileName);
    }
}
