package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value06_groupby {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(1 to 4, 2)
    val value: RDD[(Int, Iterable[Int])] = rdd.groupBy(i => i)
    /*
    将rdd根据某个元素的条件进行分组

     */
    value.collect().foreach(println)

    sc.stop()
  }
}
