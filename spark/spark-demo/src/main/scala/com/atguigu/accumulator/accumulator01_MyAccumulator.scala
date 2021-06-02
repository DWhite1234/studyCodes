package com.atguigu.accumulator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

object accumulator01_MyAccumulator {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd: RDD[String] = sc.makeRDD(List("Hello", "Hello", "Hello", "Hive", "Spark", "Spark"), 2)

    //创建累加器
    val acc = new MyAccumulator
    //注册累加器
    sc.register(acc)
    //使用
    rdd.foreach{
      case (word) =>acc.add(word)
    }
    println(acc.value)


    sc.stop()
  }
}


class MyAccumulator extends AccumulatorV2[String, mutable.Map[String, Int]] {
  val map: mutable.Map[String, Int] = mutable.Map[String, Int]()

  //是否初始化,如果map为空则已经初始化
  override def isZero: Boolean = map.isEmpty

  //将map复制给各个executor
  override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = new MyAccumulator

  //重置map
  override def reset(): Unit = map.clear()

  //map 分区内 添加数据的逻辑
  override def add(v: String): Unit = {
    if (v.startsWith("H")) {
      //需要先判断map中是否有这个key
      //利用map.getOrElse(v,0) 如果不存在这个key返回设置的默认值0
      map(v)=map.getOrElse(v,0)+1
    }
  }

  //map 分区间 合并数据的逻辑
  override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
    val map2: mutable.Map[String, Int] = other.value
    map2.foreach(
      //原理与分区间添加数据一致
      i =>map(i._1)=map.getOrElse(i._1,0)+i._2
    )
  }

  //累加器的值
  override def value: mutable.Map[String, Int] = map
}
