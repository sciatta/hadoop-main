package com.sciatta.dev.java.spark.example.sql.hive

import com.sciatta.dev.java.spark.example.sql.Config
import org.apache.spark.sql.SparkSession

/**
 * Created by yangxiaoyu on 2020/3/14<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HiveLocalSupport
 */
object HiveLocalSupport {
  def main(args: Array[String]): Unit = {
    val config = Config.getLocalConfig(getClass.getName)

    val sparkSession = SparkSession.builder().config(config).enableHiveSupport().getOrCreate()

    sparkSession.sql("create database if not exists test")
    sparkSession.sql("use test")
    sparkSession.sql("create table if not exists people(id string, name string, age int) row format delimited fields terminated by ' '")
    sparkSession.sql("load data local inpath '/Users/yangxiaoyu/work/test/sparkdatas/people' into table people")
    sparkSession.sql("select * from people").show()

    sparkSession.stop()
  }
}
