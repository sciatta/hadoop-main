package com.sciatta.hadoop.spark.example.sql.udf

import com.sciatta.hadoop.spark.example.sql.Config
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.api.java.UDF1
import org.apache.spark.sql.types.StringType

/**
 * Created by yangxiaoyu on 2020/3/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CharConvertUDF
 */
object CharConvertUDF {
  def main(args: Array[String]): Unit = {

    val config = Config.getLocalConfig(getClass.getName)
    val spark = SparkSession.builder().config(config).getOrCreate()

    val df = spark.read.text("/Users/yangxiaoyu/work/test/sparkdatas/udfdata")
    df.createTempView("data")

    // 注册 toUp udf
    spark.udf.register("toUp", new UDF1[String, String]() {
      override def call(t1: String): String = {
        t1.toUpperCase
      }
    }, StringType)

    // 注册 toLow udf
    spark.udf.register("toLow", (x: String) => x.toLowerCase())

    spark.sql("select value from data").show()

    // toUp udf
    spark.sql("select toUp(value) from data").show()

    // toLow udf
    spark.sql("select toLow(value) as to_low_value from data").show()

    spark.stop()
  }
}
