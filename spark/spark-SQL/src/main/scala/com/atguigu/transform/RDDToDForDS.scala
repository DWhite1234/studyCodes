package com.atguigu.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object RDDToDForDS {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val rdd= sc.makeRDD(List(("hello","18")))
    //转换需要导入隐式转换
    import spark.implicits._

    //默认列名转换DF
    val DF: DataFrame = rdd.toDF()
    DF.show()

    //指定列名转换DF
    val DF2: DataFrame = rdd.toDF("name", "age")
    DF2.show()

    //rdd转换ds无法指定列明,所以一般不使用 基本数据类型转换,
    val DS: Dataset[(String, String)] = rdd.toDS()
    DS.show()

    //使用对象转换
    val stuRDD: RDD[Student] = sc.makeRDD(List(Student("zs", 18)))
    val stuDS: Dataset[Student] = stuRDD.toDS()
    stuDS.show()

    spark.stop()
  }
}

case class Student(val name:String,val age:Int)