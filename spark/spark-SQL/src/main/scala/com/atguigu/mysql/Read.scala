package com.atguigu.mysql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, DataFrameReader, SparkSession}

object Read {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //读取mysql的表
    val userDf: DataFrame = spark.read.format("jdbc")
      .option("url", "jdbc:mysql://hadoop101:3306/test")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user")
      .load()
    userDf.createTempView("user")
    spark.sql("select * from user").show()

    spark.stop()
  }
}
