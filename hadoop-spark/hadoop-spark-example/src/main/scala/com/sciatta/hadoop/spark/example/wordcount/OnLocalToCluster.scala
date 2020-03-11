package com.sciatta.hadoop.spark.example.wordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by yangxiaoyu on 2020/3/10<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OnLocalToCluster 本地运行代码，发布到集群中运行
 */
object OnLocalToCluster {

  def main(args: Array[String]): Unit = {

    // 注意要先打包，保持代码一致
    val sparkConf = new SparkConf()
      .setAppName(getClass.getName)
      .setMaster("spark://node01:7077")
      .setJars(List("/Users/yangxiaoyu/work/bigdata/project/hadoop-main/hadoop-spark/hadoop-spark-example/target/hadoop-spark-example-1.0-SNAPSHOT.jar"))
      .set("spark.driver.host", "192.168.0.103")

    val sparkContext = new SparkContext(sparkConf)

    val result: Array[(String, Int)] = sparkContext.textFile("hdfs://node01:8020/test/mr/wordcount/input")
      .flatMap(_.split(","))
      .map((_, 1))
      .reduceByKey(_ + _)
      .sortBy(_._2)
      .collect()

    result.foreach(println)

    sparkContext.stop();
  }
}
