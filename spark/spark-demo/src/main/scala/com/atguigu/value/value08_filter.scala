package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value08_filter {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(1 to 4, 2)
    val value: RDD[Int] = rdd.filter(_ > 2)
    value.collect().foreach(println)

    sc.stop()
  }
}
