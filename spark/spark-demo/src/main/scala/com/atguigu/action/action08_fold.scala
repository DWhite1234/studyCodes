package com.atguigu.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object action08_fold {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,10,2,3,6,9,7,4))
    /*
    作用于aggregate一致,只有一点有区别

    分区间和分区内逻辑一致
     */
    println(rdd.aggregate(10)(_ + _,_ + _))
    sc.stop()
  }
}
