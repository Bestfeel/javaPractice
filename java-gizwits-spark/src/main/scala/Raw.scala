/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more.
 */

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.joda.time.DateTime
import org.json4s._
import org.json4s.jackson.JsonMethods._


/**
  * Created by feel on 2016/12/22.
  */
object Raw {

  /**
    * 主要是将 JDouble  转换为long  把时间 ts 的小数位给去除
    *
    * @param json
    * @return
    */
  def mongoExtract(json: JValue): String = {
    import org.json4s._
    implicit lazy val formats = org.json4s.DefaultFormats
    json match {
      case JBool(value) => value.toString
      case JDecimal(value) => value.toString
      case JInt(value) => value.toString
      case JDouble(value) => value.toLong.toString
      case JString(value) => value.toString
      case JNull => ""
      case JNothing => ""
      case jv: JValue => jv.extract[String]
    }
  }


  def main(args: Array[String]): Unit = {


    //  提取 指定类型的数据
    val typeMap = Map(
      "cmd" -> List("app2dev", "dev2app"),
      "dev" -> List("dev_online", "dev_offline", "dev_re_online"),
      "user" -> List("user_online", "user_offline"),
      "aos_cmd" -> List("command", "status"),
      "aos_dev_status" -> List("online", "offline", "re_online")
    )

    val sparkConf = new SparkConf(). //
      setAppName("Raw"). //
      set("spark.scheduler.mode", "FAIR").
      set("spark.default.parallelism", "16")
      .setMaster("local[4]")

    val sc = new SparkContext(sparkConf)

    val sourcePath = "file:///Users/feel/raw.json"
    val savePath = "file:///Users/feel/activeDeviceCount"

    val jsonFile: RDD[String] = sc.textFile(sourcePath)

    //  获取激活设备
    //    jsonFile.map(parse(_)).
    //      filter(line => typeMap.get("dev").get.contains((line \ "type").values.toString)).
    //      map(line => (mongoExtract((line \ "mac")), mongoExtract((line \ "timestamp")).toLong))
    //      .reduceByKey((v1, v2) => Math.min(v1, v2)).map(line => line._1 + "," + line._2 + "," + parseTime(line._2))
    //      .coalesce(1, shuffle = false)
    //      .saveAsTextFile(savePath)
    def parseTime(ts: Long) = new DateTime(ts * 1000L).toString("yyyy-MM-dd")

    jsonFile.map(parse(_)).
      filter(line => typeMap.get("cmd").get.contains((line \ "type").values.toString)).
      map(line => (parseTime(mongoExtract((line \ "timestamp")).toLong) + "," + mongoExtract((line \ "mac")), mongoExtract((line \ "mac"))))
      //.groupByKey().keys.coalesce(1, shuffle = false).saveAsTextFile(savePath)
      .groupByKey().keys.map(_.split(",")).map(line => (line(0), 1))
      .reduceByKey(_ + _).map(line => line._1 + "," + line._2)
      .coalesce(1, shuffle = false).saveAsTextFile(savePath)

    //  .reduceByKey((v1, v2) => Math.min(v1, v2)).map(line => line._1 + "," + line._2 + "," + parseTime(line._2))
    //.coalesce(1, shuffle = false)
    // .saveAsTextFile(savePath)


    sc.stop()
  }

}
