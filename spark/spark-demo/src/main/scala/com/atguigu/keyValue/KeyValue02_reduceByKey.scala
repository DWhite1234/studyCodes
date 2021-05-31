package com.atguigu.keyValue

import org.apache.spark.{SparkConf, SparkContext}

object KeyValue02_reduceByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(("a",1),("b",5),("a",5),("b",2)))
    //根据key规约,传递两个参数,两两相加
    //reduceByKey 有combine 进行分区内预聚合 最后进行分区间聚合
    rdd.reduceByKey((a,b)=>a+b).collect().foreach(println)

    sc.stop()
  }
}
