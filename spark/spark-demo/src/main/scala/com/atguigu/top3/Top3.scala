package com.atguigu.top3

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Top3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val value: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\agent.log")
    val value1: RDD[(String, Int)] = value.map(i => {
      val strings: Array[String] = i.split(" ")
      (strings(1)+"-"+strings(4),1)
    })

    val value2: RDD[(String, Int)] = value1.reduceByKey((x, y) => x + y)

    val value3: RDD[(String, (String, Int))] = value2.map(t => {
      val strings: Array[String] = t._1.split("-")
      (strings(0), (strings(1), t._2))
    })
    val value4: RDD[(String, Iterable[(String, Int)])] = value3.groupByKey()
    val value5: RDD[(String, List[(String, Int)])] = value4.mapValues(datas => datas.toList.sortBy(t => t._2)(Ordering.Int.reverse).take(3))
    value5.collect().foreach(println)



    sc.stop()
  }
}
