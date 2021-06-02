package com.atguigu.keyValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object KeyValue05_foldByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 3), ("a", 5), ("b", 7), ("b", 2), ("b", 4), ("b", 6), ("a", 7)), 2)

    //foldByKey 是区内与区间逻辑相同的aggregateByKey
    rdd.foldByKey(0)(math.max(_,_)).collect().foreach(println)
    Thread.sleep(Long.MaxValue)
    sc.stop()
  }
}
