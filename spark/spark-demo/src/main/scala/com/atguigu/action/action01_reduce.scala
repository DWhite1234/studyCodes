package com.atguigu.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object action01_reduce {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))
    println(rdd.reduce(_ + _))

    sc.stop()
  }
}
