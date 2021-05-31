package com.atguigu.doubleValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object DoubleValue03_subtract {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1: RDD[Int] = sc.makeRDD(1 to 4)
    val rdd2: RDD[Int] = sc.makeRDD(4 to 8)

    //差集
    rdd1.subtract(rdd2).collect().foreach(println)

    sc.stop()
  }
}
