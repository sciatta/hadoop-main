package com.sciatta.dev.java.spark.example.sql.rdd

import com.sciatta.dev.java.spark.example.sql.Config
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * Created by yangxiaoyu on 2020/3/14<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * RDDToDataFrameByCaseClass
 */
object RDDToDataFrameByCaseClass {

  case class Person(id: Int, name: String, age: Int)

  def dsl(df: DataFrame, session: SparkSession): Unit = {
    import session.implicits._

    // 打印schema
    df.printSchema()

    // 打印数据
    df.show()

    // 打印第一条数据
    val first: Row = df.first()
    println(first)
    println()

    // 打印前三条
    val top3: Array[Row] = df.head(3)
    top3.foreach(println)
    println()

    // 获取name字段
    // $ 方法调用简写，需要导入隐式转换
    df.select($"name").show
    // 获取多个字段，同时age字段+1
    df.select($"id", $"name", $"age", $"age" + 1).show()

    // 过滤条件
    val count: Long = df.filter($"age" > 30).count()
    println(count)
    println()

    // 分组
    df.groupBy($"age").count().show()
  }

  def sql(df: DataFrame, session: SparkSession): Unit = {
    // 创建临时表
    df.createTempView("person")

    session.sql("select * from person").show()

    session.sql("select name from person where age > 30").show()

    session.sql("select age, count(age) as count from person group by age").show()

    session.sql("select * from person order by age desc").show()
  }

  def main(args: Array[String]): Unit = {
    val config = Config.getLocalConfig(getClass.getName)
    val session: SparkSession = SparkSession.builder().config(config).getOrCreate()

    val context: SparkContext = session.sparkContext
    context.setLogLevel("warn")

    // 导入隐式转换
    import session.implicits._

    val df: DataFrame = context.textFile("/Users/yangxiaoyu/work/test/sparkdatas/people")
      .map(_.split(" "))
      .map(x => Person(x(0).toInt, x(1), x(2).toInt))
      .toDF()

    // DSL
    dsl(df, session)

    println("*" * 10 * 8)

    // SQL
    sql(df, session)

    context.stop()
    session.stop()
  }
}

