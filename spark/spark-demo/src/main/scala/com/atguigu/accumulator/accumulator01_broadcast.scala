package com.atguigu.accumulator

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object accumulator01_broadcast {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val dataRDD: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("a", 4)))

    //创建广播变量,每个节点只会发送一份,只读
    val broadcast: Broadcast[String] = sc.broadcast("WARNNING")

    val value = dataRDD.foreach(
      i => {
        if (i._1.contains("a")) {
          println(broadcast.value)
        }
      }
    )
    sc.stop()
  }
}
