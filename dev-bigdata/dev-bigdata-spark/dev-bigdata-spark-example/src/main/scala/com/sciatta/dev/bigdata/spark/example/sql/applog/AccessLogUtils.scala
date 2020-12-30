package com.sciatta.dev.bigdata.spark.example.sql.applog

import scala.util.matching.Regex

/**
 * Created by yangxiaoyu on 2020/3/18<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AccessLogUtils
 */
object AccessLogUtils {
  // 包含转义字符和换行
  val regex: Regex = """^(\S+) (\S+) (\S+) \[([\w:/]+\s[+\-]\d{4})\] "(\S+) (\S+) (\S+)" (\d{3}) (\d+) (\S+) (.*)""".r

  def isValidateLogLine(line: String): Boolean = {
    val options = regex.findFirstMatchIn(line)

    if (options.isEmpty) {
      false
    } else {
      true
    }
  }

  def parseLogLine(line: String): AccessLog = {

    // 从line中获取匹配的数据
    val options = regex.findFirstMatchIn(line)

    // 获取matcher
    val matcher = options.get

    // 构建返回值
    AccessLog(
      // 获取匹配字符串中第1个小括号中的值（group(0)是整个字符串）
      matcher.group(1),
      matcher.group(2),
      matcher.group(3),
      matcher.group(4),
      matcher.group(5),
      matcher.group(6),
      matcher.group(7),
      matcher.group(8).toInt,
      matcher.group(9).toLong,
      matcher.group(10),
      matcher.group(11)
    )
  }
}

case class AccessLog(
                      ipAddress: String, // IP地址
                      clientId: String, // 客户端唯一标识符
                      userId: String, // 用户唯一标识符
                      serverTime: String, // 服务器时间
                      method: String, // 请求类型/方式
                      endpoint: String, // 请求的资源
                      protocol: String, // 请求的协议名称
                      responseCode: Int, // 请求返回值：比如：200、401
                      contentSize: Long, // 返回的结果数据大小
                      url: String, //访问的url地址
                      clientBrowser: String //客户端游览器信息
                    )
