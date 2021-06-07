package com.atguigu.readAndSaveData

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object ReadData {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    conf.set("spark.sql.sources.default","json")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //1.指定格式读取
    val df: DataFrame = spark.read.json("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")
    df.show()

    //2.通用方法读取,需要用format方法指定读取的文件类型,默认读取parquet文件
    val df2: DataFrame = spark.read.format("json").load("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")
    df2.show()

    //3.全局修改默认读取文件格式,
    //conf.set("spark.sql.sources.default","json")
    val df3: DataFrame = spark.read.load("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")
    df3.show()
    spark.stop()
  }
}
