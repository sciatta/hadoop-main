package com.sciatta.dev.bigdata.spark.example.sql.rdd

import com.sciatta.dev.bigdata.spark.example.sql.Config
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

/**
 * Created by yangxiaoyu on 2020/3/14<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RDDToDataFrameByStructType
 */
object RDDToDataFrameByStructType {
  def main(args: Array[String]): Unit = {
    val config = Config.getLocalConfig(getClass.getName)
    val sparkSession = SparkSession.builder().config(config).getOrCreate()

    val rddRow: RDD[Row] = sparkSession.sparkContext.textFile("/Users/yangxiaoyu/work/test/sparkdatas/people")
      .map(_.split(" "))
      .map(x => Row(x(0).toInt, x(1), x(2).toInt))

    val schema = StructType(
      StructField("id", IntegerType) ::
        StructField("name", StringType) ::
        StructField("age", IntegerType) ::
        Nil
    )

    val df = sparkSession.createDataFrame(rddRow, schema)
    df.select("id", "name", "age").show(1)

    df.createTempView("person")
    sparkSession.sql("select * from person").show(1)

    sparkSession.stop()
  }
}
