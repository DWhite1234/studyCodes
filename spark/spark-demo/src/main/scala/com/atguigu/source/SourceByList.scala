package com.atguigu.source

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SourceByList {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val value: RDD[Int] = sc.makeRDD(1 to 10)
    value.saveAsTextFile("output")
    /*
    1.本地模式不指定分区创建
      scheduler.conf.getInt("spark.default.parallelism", totalCores)
      默认使用最大核心线程数
     */


    val value1: RDD[Int] = sc.makeRDD(1 to 10, 3)
    /*
    1.本地模式指定分区

    def positions(length: Long, numSlices: Int): Iterator[(Int, Int)] = {
      (0 until numSlices).iterator.map { i =>
        val start = ((i * length) / numSlices).toInt
        val end = (((i + 1) * length) / numSlices).toInt
        (start, end)
      }
    }

     */

    sc.stop()
  }
}
