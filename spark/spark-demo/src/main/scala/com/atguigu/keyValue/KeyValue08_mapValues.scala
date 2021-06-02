package com.atguigu.keyValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object KeyValue08_mapValues {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((1, "a"), (1, "d"), (2, "b"), (3, "c")))

    //不走shuffle
    rdd.mapValues(i=>i+"||||").collect().foreach(println)
    Thread.sleep(Long.MaxValue)
    sc.stop()
  }
}
