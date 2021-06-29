package com.atguigu.exercise

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object exercise01 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)
    // List("Hello", "Hello", "Hello", "Hello", "Spark", "Spark") =>  （Hello，4）
    val value: RDD[String] = sc.makeRDD(List("Hello", "Hello", "Hello", "Hello", "Spark", "Spark"))

    val accumulator: LongAccumulator = sc.longAccumulator
    val value1: RDD[Any] = value.map(
      i => {
        if (i == "Hello") {
          accumulator.add(1)
          (i, 1)
        }
      }
    )
    value1.collect().foreach(println)
    sc.stop()
  }
}
