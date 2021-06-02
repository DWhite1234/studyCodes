package com.atguigu.accumulator

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object accumulator01_system {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val dataRDD: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 2), ("a", 3), ("a", 4)))
    //普通算子实现
    val value: RDD[(String, Int)] = dataRDD.reduceByKey((a, b) => a + b)
    value.collect().foreach(println)

    //累加器实现(共享只写变量),只能在task上写入数据,不能返回数据
    //1.定义累加器
    val count: LongAccumulator = sc.longAccumulator("count")
    //2.使用
    dataRDD.foreach{
      case (word,cnt)=>
        count.add(cnt)
    }

    /*
    1.累加器是分布式的共享只读变量,在转换算子的数据不是最终的数据
    2.累加器要放在行动算子中
    3.累加器如果放在转换算子中,每多一个job,就会重复执行一次
     */
    println(count.value)

    sc.stop()
  }
}
