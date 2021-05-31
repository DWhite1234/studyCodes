package com.atguigu.createRDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object CreateByList {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val value: RDD[Int] = sc.parallelize(1 to 10)

    //makeRDD底层调用parallelize,一般多用makeRDD
    val value1: RDD[Int] = sc.makeRDD(2 to 10)



    sc.stop()
  }
}
