package com.atguigu.keyValue

import org.apache.spark.{SparkConf, SparkContext}

object KeyValue03_groupByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(("a",1),("b",5),("a",5),("b",2)))
    //根据key分组
    rdd.groupByKey().collect().foreach(println)


    /*
    1）reduceByKey：按照key进行聚合，在shuffle之前有combine（预聚合）操作，返回结果是RDD[K,V]。
    2）groupByKey：按照key进行分组，直接进行shuffle。
    3）开发指导：在不影响业务逻辑的前提下，优先选用reduceByKey。求和操作不影响业务逻辑，求平均值影响业务逻辑
     */
    Thread.sleep(Long.MaxValue)

    sc.stop()
  }
}
