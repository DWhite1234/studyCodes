package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value12_repartition {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4, 5, 6), 4)

    //合并分区
    rdd.repartition(2).mapPartitionsWithIndex((a, b) => b.map(i => (a, i))).collect().foreach(print)
    println()
    //扩大分区,走shuffle
    rdd.repartition(6).mapPartitionsWithIndex((a, b) => b.map(i => (a, i))).collect().foreach(print)


    /*
    对比:
      coalesce:如果要扩大分区需要,设置shuffle:true,默认为false
      repartition:默认设置了shuffle:true,repartition实际上还是调用了coalesce


     */
    sc.stop()
  }
}
