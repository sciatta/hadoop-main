package com.sciatta.hadoop.mapreduce.example.phase.combine;

import com.sciatta.hadoop.mapreduce.example.phase.inputformat.text.TextInputFormatJobLocal;
import org.apache.hadoop.mapreduce.Job;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CombinerJobLocal
 */
public class CombinerJobLocal extends TextInputFormatJobLocal {
    protected static final String INPUT_FILE_NAME = "combine/input";
    protected static final String OUTPUT_FILE_NAME = "combine/output";

    @Override
    protected String getJobName() {
        return this.getClass().getName();
    }

    @Override
    protected Class<?> getJobClass() {
        return CombinerJobLocal.class;
    }

    @Override
    protected String getInputPath() {
        return LOCAL_PARENT_PATH + INPUT_FILE_NAME;
    }

    @Override
    protected String getOutputPath() {
        return LOCAL_PARENT_PATH + OUTPUT_FILE_NAME;
    }

    @Override
    protected void configCombiner(Job job) {
        job.setCombinerClass(Combiner.class);
    }
}
