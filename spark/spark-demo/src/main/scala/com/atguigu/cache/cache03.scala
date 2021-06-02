package com.atguigu.cache

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object cache03 {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME","zt")

    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    val sc = new SparkContext(conf)
    sc.setCheckpointDir("hdfs://hadoop101:8020/checkpoint")


    val value: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\1.txt")
    val value1: RDD[String] = value.flatMap(i => i.split(" "))
    val value2: RDD[(String, Long)] = value1.map {
      case (word) => {
        (word, System.currentTimeMillis())
      }
    }
    value2.cache()
    value2.checkpoint()
    value2.collect().foreach(println)

    sc.stop()
  }
}
