package com.atguigu.serializable

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val value: RDD[Nothing] = sc.makeRDD(List())
    val test0 = new Test01
    val name:String=test0.name

    //基本数据类型默认实现了序列化,
    //想法:使用隐式转化基本数据类型 不实现序列化测试一下闭包
//    value.foreach(i=> println(test0.name))
    value.foreach(i=> println(name))
    sc.stop()
  }
}


class Test01{
  val name="zs"

}
