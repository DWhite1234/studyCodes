package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value10_distinct {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val distinctRdd: RDD[Int] = sc.makeRDD(List(1,2,1,5,2,9,6,1))
    val value1: RDD[(Int, Int)] = distinctRdd.mapPartitionsWithIndex((a, b) => b.map(i => (a, i)))
    value1.collect().foreach(print)
    println()
    println("========================去重后==========================")
    val value: RDD[Int] = distinctRdd.distinct()
    /*
    分布式去重,走shuffle
    可以设置分区数,提高并发度
     */
    val value2: RDD[(Int, Int)] = value.mapPartitionsWithIndex((a, b) => b.map(i => (a, i)))
    value2.collect().foreach(print)
    sc.stop()
  }
}
