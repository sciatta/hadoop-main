package com.sciatta.dev.java.spark.example.sql.write

import com.sciatta.dev.java.spark.example.sql.Config
import org.apache.spark.sql.SparkSession

/**
 * Created by yangxiaoyu on 2020/3/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * WriteData
 */
object WriteData {
  def main(args: Array[String]): Unit = {
    val config = Config.getLocalConfig(getClass.getName)
    val sparkSession = SparkSession.builder().config(config).getOrCreate()

    // {"name":"zhangsan1","classNum":"10","score":90}
    val df = sparkSession.read.json("/Users/yangxiaoyu/work/test/sparkdatas/write/input/score.json")

    df.createTempView("score")

    val result = sparkSession.sql("select * from score")

    // 文本格式，只允许一个字段
    // 指定目录
    // result.select("name").write.text("/Users/yangxiaoyu/work/test/sparkdatas/write/output/text")

    // json格式
    // result.write.json("/Users/yangxiaoyu/work/test/sparkdatas/write/output/json")

    // 列式存储格式
    // 使用snappy压缩
    // result.write.parquet("/Users/yangxiaoyu/work/test/sparkdatas/write/output/parquet")

    // 默认列式存储格式
    // result.write.save("/Users/yangxiaoyu/work/test/sparkdatas/write/output/default")

    // csv格式
    // 10,zhangsan1,90
    // result.write.csv("/Users/yangxiaoyu/work/test/sparkdatas/write/output/csv")

    // 分区
    // 以字段name分区 name=xxx/...
    // result.write.partitionBy("name").json("/Users/yangxiaoyu/work/test/sparkdatas/write/output/pone")

    // 分区
    // 以字段name和classNum分区 name=xxx/classNum=xxx/...
    // 文件内容 {"score":90}
    result.write.partitionBy("name", "classNum").json("/Users/yangxiaoyu/work/test/sparkdatas/write/output/ptwo")

    sparkSession.stop()
  }
}
