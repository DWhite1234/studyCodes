package com.atguigu.ds

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object dsCreateMethods {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val ss: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //1.使用基本序列创建ds
    ss.stop()
  }
}
