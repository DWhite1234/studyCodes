package com.atguigu.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object action06_takeOrdered {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,10,2,3,6,9,7,4))

    rdd.takeOrdered(3)(Ordering.Int.reverse).foreach(print)
    sc.stop()
  }
}
