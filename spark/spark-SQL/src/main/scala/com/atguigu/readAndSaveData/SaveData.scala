package com.atguigu.readAndSaveData

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object SaveData {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    conf.set("spark.sql.sources.default","json")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val df: DataFrame = spark.read.json("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")
    //1.指定文件保存格式
//    df.write.json("save1")

    //2.通用存储,format指定格式,默认parquet文件保存
//    df.write.format("json").save("save2")

    //3.全局默认格式保存
//    df.write.save("save3")

    //4.更改保存模式
    /*
    Append :追加保存
    Ignore :文件存在不报错,也不执行,文件不存在,创建
    Overwrite :覆盖保存,先删除在创建
    ErrorIfExists :如果文件存在则直接报错 (默认)
     */
    df.write.mode(SaveMode.Append).save("save3")
    spark.stop()
  }
}
