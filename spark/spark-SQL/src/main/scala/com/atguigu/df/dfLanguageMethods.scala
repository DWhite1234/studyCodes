package com.atguigu.df

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object dfLanguageMethods {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val ss: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    //1.读取文件直接创建df
    val df1: DataFrame = ss.read.json("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")
    df1.show()

    //2.sql风格创建,sql风格需要先创建视图,需要先有数据源
    df1.createTempView("user")
    val df2: DataFrame = ss.sql("select * from user")
    df2.show()

    //dsl风格,不需要创建视图
    df1.select("*").show()
    df1.select("*").where("age > 18").show()



    ss.stop()
  }
}
