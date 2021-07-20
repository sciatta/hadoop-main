package com.sciatta.dev.java.spark.example.sql.mysql

import com.sciatta.dev.java.spark.example.sql.Config

import java.util.Properties
import org.apache.spark.sql.SparkSession

/**
 * Created by yangxiaoyu on 2020/3/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ReadWrite
 */
object ReadWrite {
  def main(args: Array[String]): Unit = {
    val url = "jdbc:mysql://node03:3306/spark"

    val readTableName = "user"
    val writeTableName = "newuser"

    val properties = new Properties()
    properties.setProperty("user", "root")
    properties.setProperty("password", "root")

    // val config = Config.getLocalConfig(getClass.getName)
    val config = Config.getServerConfig(getClass.getName)

    val sparkSession = SparkSession.builder().config(config).getOrCreate()

    // 读取数据
    val readDF = sparkSession.read.jdbc(url, readTableName, properties)
    // readDF.printSchema()
    // readDF.show()

    // 创建临时表
    readDF.createTempView("user")

    // 处理数据
    val writeDF = sparkSession.sql("select id, name, age, age-10 as newhope from user")

    // 写入数据
    // mode
    // overwrite  表示覆盖，如果表不存在，创建
    // append     表示追加，如果表不存在，创建
    // ignore     表示忽略，如果表存在，不进行任何操作
    // error      如果表存在，报错（默认选项）
    writeDF.write.mode("overwrite").jdbc(url, writeTableName, properties)

    sparkSession.stop()
  }
}
