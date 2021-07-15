package com.atguigu.cache

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object cache01 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val value: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\1.txt")
    val value1: RDD[String] = value.flatMap(i => i.split(" "))
    val value2: RDD[(String, Int)] = value1.map {
      case (word) => {
        println("************")
        (word, 1)
      }
    }
    /*
    1.调用缓存,cache调用不会立即生效,当遇到行动算子的时候才会生效
    2.cache 底层调用的persist,策略默认为MEMORY_ONLY 
    5.缓存存储在内存中
    3.使用persist 可以指定缓存策略
    4.使用缓存会增加血缘关系,但是不会中断血缘关系
    5.某些算子自带缓存如reduceByKey
     */
    value2.cache()
    value2.persist(StorageLevel.MEMORY_ONLY)
    value2.collect().foreach(println)
    println("=========================")
    value2.collect().foreach(println)
    //释放缓存
    value2.unpersist()

    sc.stop()
  }
}
