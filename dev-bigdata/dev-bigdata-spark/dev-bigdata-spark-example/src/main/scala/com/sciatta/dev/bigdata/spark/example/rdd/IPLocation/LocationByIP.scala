package com.sciatta.dev.bigdata.spark.example.rdd.IPLocation

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by yangxiaoyu on 2020/3/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LocationByIP
 */
object LocationByIP {
  def ipToLong(ip: String): Long = {
    val ips: Array[String] = ip.split("\\.")
    var result = 0L

    for (ip <- ips) {
      result = ip.toLong | result << 8L
    }

    result
  }

  def search(ip: Long, ipRange: Array[(String, String, String, String)]): Int = {
    var start = 0
    var end = ipRange.length - 1

    while (start <= end) {
      val middle = (start + end) / 2

      if (ip >= ipRange(middle)._1.toLong && ip <= ipRange(middle)._2.toLong) {
        return middle
      }

      if (ip < ipRange(middle)._1.toLong) {
        end = middle - 1
      }

      if (ip > ipRange(middle)._2.toLong) {
        start = middle + 1
      }
    }
    // 未找到匹配
    -1
  }

  def main(args: Array[String]): Unit = {
    val context = new SparkContext(new SparkConf().setAppName(getClass.getName).setMaster("local[2]"))
    context.setLogLevel("warn")

    // (ip开始数字, ip结束数字, 经度, 维度)
    val ipRange: RDD[(String, String, String, String)] = context.textFile("/Users/yangxiaoyu/work/test/sparkdatas/ip/ip.txt")
      .map(_.split("\\|"))
      .map(a => (a(2), a(3), a(a.length - 2), a(a.length - 1)))
    // 将ip范围数据在driver端构建，然后分发到每一个worker节点的Executor进程中
    val broadcast: Broadcast[Array[(String, String, String, String)]] = context.broadcast(ipRange.collect())

    // http请求数据
    val data: Array[((String, String), Int)] = context.textFile("/Users/yangxiaoyu/work/test/sparkdatas/ip/20090121000132.394251.http.format")
      .map(_.split("\\|")(1))
      .map(ipToLong(_))
      .mapPartitions(iter => {
        val ipr: Array[(String, String, String, String)] = broadcast.value
        iter.map(ip => {
          val index: Int = search(ip, ipr)
          if (index != -1) {
            val result: (String, String, String, String) = ipr(index)
            ((result._3, result._4), 1)
          } else {
            ((null, null), -1)
          }
        })
      })
      .filter(test => test._2 != -1)
      .reduceByKey(_ + _)
      .sortBy(_._2)
      .collect()

    data.foreach(println)

    context.stop()
  }
}
