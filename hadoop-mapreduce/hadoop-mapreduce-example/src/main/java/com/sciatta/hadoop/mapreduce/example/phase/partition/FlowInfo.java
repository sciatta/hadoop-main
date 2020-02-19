package com.sciatta.hadoop.mapreduce.example.phase.partition;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowInfo
 */
public class FlowInfo implements Writable {
    private String phoneNum;
    private Integer upPackageNum;
    private Integer upPayLoad;
    private Integer downPackageNum;
    private Integer downPayLoad;

    public void write(DataOutput out) throws IOException {
        // read 和 write 必须一致，否则会影响序列化
        out.writeUTF(phoneNum);
        out.writeInt(upPackageNum);
        out.writeInt(upPayLoad);
        out.writeInt(downPackageNum);
        out.writeInt(downPayLoad);
    }

    public void readFields(DataInput in) throws IOException {
        this.phoneNum = in.readUTF();
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return phoneNum + "\t" + upPackageNum + "\t" + upPayLoad + "\t" + downPackageNum + "\t" + downPayLoad;
    }

    public FlowInfo parse(String value) {
        String[] info = value.split("\t");
        String phoneNum = info[1];
        String upPackageNum = info[6];
        String downPackageNum = info[7];
        String upPayLoad = info[8];
        String downPayLoad = info[9];

        FlowInfo flowInfo = new FlowInfo();
        flowInfo.setUpPackageNum(Integer.valueOf(upPackageNum));
        flowInfo.setDownPackageNum(Integer.valueOf(downPackageNum));
        flowInfo.setUpPayLoad(Integer.valueOf(upPayLoad));
        flowInfo.setDownPayLoad(Integer.valueOf(downPayLoad));
        flowInfo.setPhoneNum(phoneNum);

        return flowInfo;
    }

    public static <T extends FlowInfo> String formatInfo(Iterable<T> infos) {
        int upPackageNum = 0;
        int upPayLoad = 0;
        int downPackageNum = 0;
        int downPayLoad = 0;

        for (T info : infos) {
            upPackageNum += info.getUpPackageNum();
            upPayLoad += info.getUpPayLoad();
            downPackageNum += info.getDownPackageNum();
            downPayLoad += info.getDownPayLoad();
        }

        return upPackageNum + "\t" + upPayLoad + "\t" + downPackageNum + "\t" + downPayLoad;
    }
}
