package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value01_map {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd: RDD[Int] = sc.makeRDD(1 to 4, 2)

    //将一个变量,根据需求转变形式
    //例如 a => (a,1)   (a,1)=>a
    val value: RDD[(Int, Int)] = rdd.map((_, 1))
    value.collect().foreach(println)
    Thread.sleep(Long.MaxValue)
    sc.stop()
  }
}
