package com.atguigu.fun

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object UDF {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val df: DataFrame = spark.read.json("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")
    df.createTempView("User")
    //使用UDF
    spark.sql("select avg(age) from User").show()

    //自定义UDF 年龄乘 2
    spark.udf.register("addAge", (x:Int) => x * 2)
    spark.sql("select addAge(age) from User").show()
    spark.stop()
  }
}
