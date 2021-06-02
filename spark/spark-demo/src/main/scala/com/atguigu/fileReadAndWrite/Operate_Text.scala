package com.atguigu.fileReadAndWrite

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Operate_Text {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //读取text文件
    val value: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\1.txt")
    value.collect().foreach(println)
    //写出text文件,如果文件名重复,则会报错
    value.saveAsTextFile("text")

    sc.stop()
  }
}
