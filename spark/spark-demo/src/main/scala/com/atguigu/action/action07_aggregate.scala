package com.atguigu.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object action07_aggregate {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,10,2,3,6,9,7,4))
    /*
    作用于aggregateByKey一直,只有一点有区别

    在进行分区间进行聚合的时候 还要在加一次初始值
     */
    println(rdd.aggregate(10)(_ + _, _ + _))
    sc.stop()
  }
}
