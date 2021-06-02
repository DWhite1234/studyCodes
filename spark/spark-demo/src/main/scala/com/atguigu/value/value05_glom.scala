package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value05_glom {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(1 to 4, 2)
    val value: RDD[Array[Int]] = rdd.glom()
    /*
    将分区拆分为数组返回
     */
    value.collect().foreach(i=> println(i.toList))
    Thread.sleep(Long.MaxValue)
    sc.stop()
  }
}
