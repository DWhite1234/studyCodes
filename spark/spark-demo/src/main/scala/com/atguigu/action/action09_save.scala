package com.atguigu.action

import org.apache.spark.{SparkConf, SparkContext}

object action09_save {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)


    sc.stop()
  }
}
