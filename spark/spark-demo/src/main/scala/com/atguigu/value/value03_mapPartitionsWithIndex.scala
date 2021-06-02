package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value03_mapPartitionsWithIndex {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(1 to 4, 2)
    /*
    带分区号的map,传入两个参数
    第一个参数:分区号
    第二个参数:分区内可迭代数据变量

    通过 分区内可迭代数据变量 调用map 给分区内每个数据添加分区号
     */
    val value: RDD[(Int, Int)] = rdd.mapPartitionsWithIndex((a, b) => b.map(i => (a, i)))
    value.collect().foreach(println)
    Thread.sleep(Long.MaxValue)
    sc.stop()
  }
}
