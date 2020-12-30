package com.sciatta.dev.bigdata.spark.example.rdd.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by yangxiaoyu on 2020/3/10<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OnLocal
 */
object OnLocal {
  def main(args: Array[String]): Unit = {
    val INPUT_PATH = "/Users/yangxiaoyu/work/test/mrdatas/wordcount/input";

    // 构建 SparkConf
    val sparkConf: SparkConf = new SparkConf().setAppName(getClass.getName).setMaster("local[2]");

    // 构建 SparkContext
    val sparkContext: SparkContext = new SparkContext(sparkConf)

    // 设置日志级别
    sparkContext.setLogLevel("warn");

    // 读取文件 => 行数据
    val lines: RDD[String] = sparkContext.textFile(INPUT_PATH)

    // 行数据 => 单词
    val words: RDD[String] = lines.flatMap(_.split(","))

    // 单词 => (单词, 1)
    val oneWord: RDD[(String, Int)] = words.map((_, 1))

    // (单词, 1) => (单词, 总数)
    val wordCount: RDD[(String, Int)] = oneWord.reduceByKey(_ + _)

    // (单词, 总数) 按元组第二位的总数排序
    val sortRDD: RDD[(String, Int)] = wordCount.sortBy(_._2)

    // 返回结果
    val result: Array[(String, Int)] = sortRDD.collect()

    // 打印结果
    result.foreach(println)

    // 关闭 SparkContext
    sparkContext.stop()
  }
}
