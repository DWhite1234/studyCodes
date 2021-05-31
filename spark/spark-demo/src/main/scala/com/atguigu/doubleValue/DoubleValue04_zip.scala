package com.atguigu.doubleValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object DoubleValue04_zip {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1: RDD[Int] = sc.makeRDD(1 to 4)
    val rdd3: RDD[Int] = sc.makeRDD(4 to 7,2)
    val rdd2: RDD[Int] = sc.makeRDD(4 to 8)

    //拉链,与scala的函数有所区别,如果有一个不能匹配拉链,则会报错,分区数不同也会报错
    rdd1.zip(rdd3).collect().foreach(println)

    sc.stop()
  }
}
