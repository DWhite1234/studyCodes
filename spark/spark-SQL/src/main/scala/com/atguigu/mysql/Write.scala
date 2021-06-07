package com.atguigu.mysql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object Write {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //读取mysql
    val df: DataFrame = spark.read.format("jdbc")
      .option("url", "jdbc:mysql://hadoop101:3306/test")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user").
      load()

    //写入mysql,注意写入模式一定不能写成覆盖
    df.write.format("jdbc")
      .option("url", "jdbc:mysql://hadoop101:3306/test")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user")
      .mode(SaveMode.Append)
      .save()


    spark.stop()
  }
}
