package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value04_flatMap {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val listRDD=sc.makeRDD(List(List(1,2),List(3,4),List(5,6),List(7)), 2)
    val value: RDD[Int] = listRDD.flatMap(i => i)
    /*
    相当于先map 在flat将集合类型数据打散,中间的逻辑是map 的逻辑

     */
    value.collect().foreach(println)
    sc.stop()
  }
}
