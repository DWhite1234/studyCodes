package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value11_coalesce {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4, 5, 6, 7), 4)

    //合并分区
    val value: RDD[Int] = rdd.coalesce(2)
    value.mapPartitionsWithIndex((a, b) => b.map(i => (a, i))).collect().foreach(print)
    println()
    //扩大分区,需要设置shuffle:true
    rdd.coalesce(6, true).mapPartitionsWithIndex((a, b) => b.map(i => (a, i))).collect().foreach(print)
    sc.stop()
  }
}
