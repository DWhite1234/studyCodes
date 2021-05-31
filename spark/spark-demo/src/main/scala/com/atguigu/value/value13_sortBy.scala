package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value13_sortBy {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(2, 1, 3, 4, 6, 5))
    //可以设置排序条件,默认升序
    rdd.sortBy(i=>i).collect().foreach(println)
    sc.stop()
  }
}
