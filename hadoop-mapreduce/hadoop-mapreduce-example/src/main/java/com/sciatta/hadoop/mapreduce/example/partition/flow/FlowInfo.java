package com.sciatta.hadoop.mapreduce.example.partition.flow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowInfo
 */
class FlowInfo implements Writable {
    private Integer upPackageNum;
    private Integer upPayLoad;
    private Integer downPackageNum;
    private Integer downPayLoad;

    public void write(DataOutput out) throws IOException {
        // read 和 write 必须一致，否则会影响序列化
        out.writeInt(upPackageNum);
        out.writeInt(upPayLoad);
        out.writeInt(downPackageNum);
        out.writeInt(downPayLoad);
    }

    public void readFields(DataInput in) throws IOException {
        this.upPackageNum = in.readInt();
        this.upPayLoad = in.readInt();
        this.downPackageNum = in.readInt();
        this.downPayLoad = in.readInt();
    }

    public Integer getUpPackageNum() {
        return upPackageNum;
    }

    public void setUpPackageNum(Integer upPackageNum) {
        this.upPackageNum = upPackageNum;
    }

    public Integer getUpPayLoad() {
        return upPayLoad;
    }

    public void setUpPayLoad(Integer upPayLoad) {
        this.upPayLoad = upPayLoad;
    }

    public Integer getDownPackageNum() {
        return downPackageNum;
    }

    public void setDownPackageNum(Integer downPackageNum) {
        this.downPackageNum = downPackageNum;
    }

    public Integer getDownPayLoad() {
        return downPayLoad;
    }

    public void setDownPayLoad(Integer downPayLoad) {
        this.downPayLoad = downPayLoad;
    }
}
