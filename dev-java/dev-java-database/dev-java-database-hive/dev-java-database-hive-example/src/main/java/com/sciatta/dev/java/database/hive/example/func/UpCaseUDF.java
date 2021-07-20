package com.sciatta.dev.java.database.hive.example.func;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;


/**
 * Created by yangxiaoyu on 2020/4/1<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UpCaseUDF
 */
public class UpCaseUDF extends UDF {
    // 默认情况下UDF需要提供evaluate方法，hive默认调用
    public Text evaluate(Text text) {
        if (text == null)
            return null;

        return new Text(text.toString().toUpperCase());
    }
}
