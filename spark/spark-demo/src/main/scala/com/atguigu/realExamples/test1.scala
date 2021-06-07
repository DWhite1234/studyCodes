package com.atguigu.realExamples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object test1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val value: List[Int] = sc.broadcast(List(1, 3, 4, 6)).value
    val ints = List(7, 8, 9)

    val strings = List("null")

    val ints1: List[Int] = value.intersect(ints)



    val ints2: List[Int] = ints.intersect(strings)
    println(ints2)
    println(ints2 == Nil)

    println("1111"+(List() != Nil))


//    val bool: Boolean = value.contains(-1)
//    println(bool)
    println("========================flatMap==========================")
    val value1: RDD[Nothing] = sc.makeRDD(List())
    value1.flatMap(i=>i).collect().foreach(println)
    sc.stop()
  }
}
