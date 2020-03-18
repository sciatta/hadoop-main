package com.sciatta.hadoop.spark.example.sql

import org.apache.spark.SparkConf

/**
 * Created by yangxiaoyu on 2020/3/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Config
 */
object Config {
  def getLocalConfig(appName: String): SparkConf = {
    // 自定义derby.log位置
    System.setProperty("derby.system.home", "/Users/yangxiaoyu/work/test/sparkdatas/hivelocal")

    val sparkConf = new SparkConf()

    sparkConf
      .setMaster("local[2]")

      .setAppName(appName)

      // 自定义数据仓库数据位置
      .set("spark.sql.warehouse.dir", "/Users/yangxiaoyu/work/test/sparkdatas/hivelocal/spark-warehouse")

      // 自定义元数据位置
      .set("javax.jdo.option.ConnectionURL", "jdbc:derby:;databaseName=/Users/yangxiaoyu/work/test/sparkdatas/hivelocal/metastore_db;create=true")

    sparkConf
  }

  def getServerConfig(appName: String): SparkConf = {
    val sparkConf = new SparkConf()

    sparkConf.setAppName(appName)

    sparkConf
  }
}
