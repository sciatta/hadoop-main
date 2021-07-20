package com.sciatta.dev.java.hadoop.hdfs.example.smallfile.sequencefile.impl;

import com.sciatta.dev.java.hadoop.hdfs.example.smallfile.sequencefile.AbstractSequenceFile;
import org.apache.hadoop.io.SequenceFile;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/23<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * NoneCompressed
 */
public class NoneCompressed extends AbstractSequenceFile {

    protected SequenceFile.Writer.Option getCompressed() {
        return SequenceFile.Writer.compression(SequenceFile.CompressionType.NONE);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "none";
        AbstractSequenceFile file = new NoneCompressed();

        file.toHdfs(fileName);

        file.toLocal(fileName);
    }
}
