package com.atguigu.sparkSession

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSessionTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    val ss: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    //读取文件,创建df
    val df: DataFrame = ss.read.json("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")
    df.show()

    ss.stop()
  }
}
