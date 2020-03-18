package com.sciatta.hadoop.spark.example.sql.applog

import java.util.Properties

import com.sciatta.hadoop.spark.example.sql.Config
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
 * Created by yangxiaoyu on 2020/3/18<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LogAnalysis
 */
object LogAnalysis {
  val url = "jdbc:mysql://node03:3306/log"

  val properties = new Properties()
  properties.setProperty("user", "root")
  properties.setProperty("password", "root")

  def main(args: Array[String]): Unit = {
    val conf = Config.getLocalConfig(getClass.getName)
    val session = SparkSession.builder().config(conf).getOrCreate()
    val context = session.sparkContext

    context.setLogLevel("warn")

    // rdd -> df -> view
    val rdd: RDD[AccessLog] = context.textFile("/Users/yangxiaoyu/work/test/sparkdatas/access.log")
      .filter(line => AccessLogUtils.isValidateLogLine(line))
      .map(line => AccessLogUtils.parseLogLine(line))

    import session.implicits._
    val df = rdd.toDF()

    df.createTempView("access")

    // 求contentSize的平均值，最大值以及最小值
    session.sql(
      """
        |select
        |date_sub(from_unixtime(unix_timestamp(),'yyyy-MM-dd'),1) as time,
        |AVG(contentSize) as avg_contentSize,
        |MAX(contentSize) as max_contentSize,
        |MIN(contentSize) as min_contentSize
        |from access
        |""".stripMargin).write.jdbc(url, "t_contentSizeInfo", properties)

    // 求 pv 和 uv
    // PV -- page view 网站浏览量
    // UV -- unique visitor 独立访客
    session.sql(
      """
        |select
        |date_sub(from_unixtime(unix_timestamp(),'yyyy-MM-dd'),1) as time,
        |count(*) as pv,
        |count(distinct ipAddress) as uv
        |from access
        |""".stripMargin).write.jdbc(url, "t_uv_pv", properties)

    // 求各个响应码出现的次数
    session.sql(
      """
        |select
        |date_sub(from_unixtime(unix_timestamp(),'yyyy-MM-dd'),1) as time,
        |responseCode as code,
        |count(*) as count
        |from access
        |group by responseCode
        |""".stripMargin).write.jdbc(url,"t_responseCode",properties)

    // 求访问url次数最多的前N位
    session.sql(
      """
        |select
        |*,date_sub(from_unixtime(unix_timestamp(),'yyyy-MM-dd'),1) as time
        |from (
        |select
        |url as url,
        |count(*) as count
        |from access
        |group by url) t
        |order by t.count desc limit 5
        |""".stripMargin).write.jdbc(url,"t_url",properties)

    // 求各个请求方式出现的次数
    session.sql(
      """
        |select
        |date_sub(from_unixtime(unix_timestamp(),'yyyy-MM-dd'),1) as time,
        |method as method,
        |count(*) as count
        |from access
        |group by method
        |""".stripMargin).write.jdbc(url,"t_method",properties)

    session.stop()
  }
}
