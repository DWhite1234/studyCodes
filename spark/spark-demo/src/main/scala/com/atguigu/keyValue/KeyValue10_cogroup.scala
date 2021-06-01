package com.atguigu.keyValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object KeyValue10_cogroup {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //3.1 创建第一个RDD
    val rdd: RDD[(Int, Any)] = sc.makeRDD(Array((1,("a",2)),(2,"b"),(3,"c")))

    //3.2 创建第二个RDD
    val rdd1: RDD[(Int, Int)] = sc.makeRDD(Array((1,4),(2,5),(4,6)))

    /*
      两个rdd cogroup的流程是

      当key相同时,把value作为一个集合,如果key和另一个key匹配上了,将匹配上的value也作为一个集合
      如:
        (1,(CompactBuffer((a,2)),CompactBuffer(4)))
        (2,(CompactBuffer(b),CompactBuffer(5)))
        (3,(CompactBuffer(c),CompactBuffer()))
        (4,(CompactBuffer(),CompactBuffer(6)))
     */
    rdd.cogroup(rdd1).collect().foreach(println)
    sc.stop()
  }
}
