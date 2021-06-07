package com.atguigu.transform

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object DForDStoRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val DF: DataFrame = spark.read.json("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")

    import spark.implicits._
    //df转换为rdd
    val rdd: RDD[Row] = DF.rdd
    rdd.collect().foreach(println)

    spark.stop()
  }
}
