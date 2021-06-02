package com.atguigu.cache

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object cache02 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setCheckpointDir("checkpoint")


    val value: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\1.txt")
    val value1: RDD[String] = value.flatMap(i => i.split(" "))
    val value2: RDD[(String, Long)] = value1.map {
      case (word) => {
        (word, System.currentTimeMillis())
      }
    }
    value2.cache()
    value2.checkpoint()
    /*
    1.检查点将rdd结果写入磁盘
    2.检查点需要设置文件存储路径 本地或hdfs
    3.检查点会切断血缘关系
    4.设置检查点后,为了数据安全,会额外起一个job从血缘开始执行,额外运行的job的结果缓存下来
    5.检查点一般配合cache使用
     */
    value2.collect().foreach(println)
    println("=========================")
    value2.collect().foreach(println)
    println("=========================")
    value2.collect().foreach(println)
    Thread.sleep(Long.MaxValue)
    //释放缓存
    value2.unpersist()

    sc.stop()

    /*
    缓存和检查点的区别:
      1.cache只是把数据保存,不会切断血缘,检查点会切断血缘
      2.cache通常存储在内存中,检查点通常存储在磁盘上
      3.cache不会额外启动一个job,而检查点会
      4.检查点一般配合cache使用
      5.使用了缓存,记得要释放缓存


     */
  }
}
