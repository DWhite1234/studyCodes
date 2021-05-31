package com.atguigu.wc
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("yarn")
    val sc = new SparkContext(conf)
    val value: RDD[String] = sc.textFile(args(0))
    val value1: RDD[String] = value.flatMap(i => i.split(" "))
    val value2: RDD[(String, Int)] = value1.map(i => (i, 1))
    val value3: RDD[(String, Int)] = value2.reduceByKey((i, j) => i + j)
    value3.collect().foreach(println)
    sc.stop()
  }
}
