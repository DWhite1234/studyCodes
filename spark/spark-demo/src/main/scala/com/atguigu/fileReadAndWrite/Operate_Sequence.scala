package com.atguigu.fileReadAndWrite

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Operate_Sequence {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val value1: RDD[(String, Int)] = sc.makeRDD(List(("hello", 1), ("hive", 2)))
    //写出Sequence文件,如果文件名重复,则会报错,必须是k-v 对的形式
    value1.saveAsSequenceFile("Sequence")

    //读取Sequence 二进制文件,必须指定读取的文件的泛型
    val value: RDD[(String, Int)] = sc.sequenceFile[String, Int]("Sequence")
    value.collect().foreach(println)

    sc.stop()
  }
}
