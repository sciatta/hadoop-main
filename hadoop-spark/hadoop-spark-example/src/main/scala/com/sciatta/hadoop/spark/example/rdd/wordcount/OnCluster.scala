package com.sciatta.hadoop.spark.example.rdd.wordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by yangxiaoyu on 2020/3/10<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OnCluster
 */
object OnCluster {

  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      throw new IllegalArgumentException("parameter number must be 2")
    }

    val sparkContext = new SparkContext(new SparkConf().setAppName(getClass.getName))

    sparkContext.textFile(args(0))
      .flatMap(_.split(","))
      .map((_, 1))
      .reduceByKey(_ + _)
      .sortBy(_._2)
      .saveAsTextFile(args(1))

    sparkContext.stop();
  }
}
