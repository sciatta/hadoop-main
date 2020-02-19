package com.sciatta.hadoop.mapreduce.example.phase.inputformat.custom;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CustomSequenceFileInputFormat
 */
public class CustomSequenceFileInputFormat extends FileInputFormat<Text, BytesWritable> {
    private Text key;
    private BytesWritable value;
    private FileSplit fileSplit;
    private boolean read;
    private FSDataInputStream in;

    public RecordReader<Text, BytesWritable> createRecordReader(InputSplit split, final TaskAttemptContext context) throws IOException, InterruptedException {
        return new RecordReader<Text, BytesWritable>() {
            @Override
            public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
                key = new Text();
                value = new BytesWritable();
                fileSplit = (FileSplit) split;
                read = false;
            }

            @Override
            public boolean nextKeyValue() throws IOException, InterruptedException {
                if (!read) {
                    byte[] data = new byte[(int) fileSplit.getLength()];
                    in = fileSplit.getPath().getFileSystem(context.getConfiguration()).open(fileSplit.getPath());
                    IOUtils.readFully(in, data, 0, data.length);

                    key.set(fileSplit.getPath().getName());
                    value.set(data, 0, data.length);

                    read = true; // 只读一次
                    return true;
                }

                return false;
            }

            @Override
            public Text getCurrentKey() throws IOException, InterruptedException {
                return key;
            }

            @Override
            public BytesWritable getCurrentValue() throws IOException, InterruptedException {
                return value;
            }

            @Override
            public float getProgress() throws IOException, InterruptedException {
                return read ? 1.0f : 0.0f;
            }

            @Override
            public void close() throws IOException {
                IOUtils.closeStream(in);
            }
        };
    }

    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        // 不可切分
        return false;
    }
}
