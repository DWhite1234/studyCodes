package com.atguigu.keyValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object KeyValue04_aggregateByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 3), ("a", 5), ("b", 7), ("b", 2), ("b", 4), ("b", 6), ("a", 7)), 2)
    /*
      zeroValue:分区内初始值,每个分区都有且相同
      seqOp:分区内的函数处理逻辑
      combOp:分区间的函数处理逻辑
     */
    //需求:取出每个分区,相同key的最大值,最后相加
    rdd.aggregateByKey(0)((a, b) => math.max(a, b), ((x,y) => x+y)).collect().foreach(println)

    sc.stop()
  }
}
