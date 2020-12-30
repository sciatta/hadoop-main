package com.sciatta.dev.bigdata.hadoop.mr.example.phase.order;

import com.sciatta.dev.bigdata.hadoop.mr.example.phase.partition.FlowInfo;
import org.apache.hadoop.io.WritableComparable;

/**
 * Created by yangxiaoyu on 2020/1/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SortedFlowInfo
 */
public class SortedFlowInfo extends FlowInfo implements WritableComparable<SortedFlowInfo> {

    @Override
    public SortedFlowInfo parse(String value) {
        String[] info = value.split("\t");
        String phoneNum = info[0];
        String upPackageNum = info[1];
        String downPackageNum = info[3];
        String upPayLoad = info[2];
        String downPayLoad = info[4];

        SortedFlowInfo flowInfo = new SortedFlowInfo();
        flowInfo.setUpPackageNum(Integer.valueOf(upPackageNum));
        flowInfo.setDownPackageNum(Integer.valueOf(downPackageNum));
        flowInfo.setUpPayLoad(Integer.valueOf(upPayLoad));
        flowInfo.setDownPayLoad(Integer.valueOf(downPayLoad));
        flowInfo.setPhoneNum(phoneNum);

        return flowInfo;
    }

    public int compareTo(SortedFlowInfo o) {
        int result = this.getUpPackageNum().compareTo(o.getUpPackageNum());

        if (result == 0) {
            result = this.getUpPayLoad().compareTo(o.getUpPayLoad());
        }

        return result;
    }
}
