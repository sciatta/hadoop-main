package com.sciatta.hadoop.hdfs.example.smallfile.sequencefile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/23<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AbstractSequenceFile
 */
public abstract class AbstractSequenceFile {
    private static final String[] DATA = {
            "Hadoop, including HDFS, is well suited for distributed storage and distributed processing using commodity hardware. It is fault tolerant, scalable, and extremely simple to expand. MapReduce, well known for its simplicity and applicability for large set of distributed applications, is an integral part of Hadoop.",
            "HDFS is highly configurable with a default configuration well suited for many installations. Most of the time, configuration needs to be tuned only for very large clusters.",
            "Hadoop is written in Java and is supported on all major platforms.",
            "Hadoop supports shell-like commands to interact with HDFS directly.",
            "The NameNode and Datanodes have built in web servers that makes it easy to check current status of the cluster."
    };

    private static final String PATH = "hdfs://192.168.2.100:8020/sequence/";
    private static final String LOCAL_PATH = "/Users/yangxiaoyu/work/test/sequence/";

    private IntWritable key = new IntWritable();
    private Text value = new Text();
    protected Configuration configuration = new Configuration();

    public AbstractSequenceFile() {
        configuration.set("dfs.replication", "2");
    }

    protected abstract SequenceFile.Writer.Option getCompressed();

    public void toHdfs(String fileName) throws IOException {
        toHdfs(fileName, 10000);
    }

    public void toHdfs(String fileName, int times) throws IOException {
        SequenceFile.Writer writer = null;
        try {
            SequenceFile.Writer.Option pathOption = SequenceFile.Writer.file(new Path(PATH + fileName));
            SequenceFile.Writer.Option keyOption = SequenceFile.Writer.keyClass(IntWritable.class);
            SequenceFile.Writer.Option valueOption = SequenceFile.Writer.valueClass(Text.class);
            SequenceFile.Writer.Option compressedOption = getCompressed();

            writer = SequenceFile.createWriter(configuration, pathOption, keyOption, valueOption, compressedOption);

            for (int i = 0; i < times; i++) {
                key.set(i);
                value.set("[" + i + "] " + DATA[i % DATA.length]);
                writer.append(key, value);
            }
        } finally {
            IOUtils.closeStream(writer);
        }

    }

    public void toLocal(String fileName) throws IOException {
        SequenceFile.Reader reader = null;
        FileWriter out = null;
        try {
            out = new FileWriter(LOCAL_PATH + fileName);

            SequenceFile.Reader.Option pathOption = SequenceFile.Reader.file(new Path(PATH + fileName));
            reader = new SequenceFile.Reader(configuration, pathOption);

            Writable k = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), configuration);
            Writable v = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), configuration);

            while (reader.next(k, v)) {
                out.append(String.format("[%s%s]\t%s\t%s\n", reader.getPosition(), reader.syncSeen() ? "sync" : "", k, v));
            }
        } finally {
            IOUtils.closeStream(reader);
            IOUtils.closeStream(out);
        }
    }

}
