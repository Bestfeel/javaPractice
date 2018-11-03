package com.github.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by feel on 2017/7/19.
  */
object App {


  class ProtonFlux(id: Int, name: String)

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf(). //
      setAppName("Raw"). //
      set("spark.scheduler.mode", "FAIR").
      set("spark.default.parallelism", "16")
      .setJars(SparkContext.jarOfClass(this.getClass).toSeq)
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .setMaster("local[4]")
      .registerKryoClasses(Array(classOf[ProtonFlux]))
    val sc = new SparkContext(sparkConf)


    println(SparkContext.jarOfClass(this.getClass).toSeq)

  }

}
