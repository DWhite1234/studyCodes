package com.atguigu.ds

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}


object dsCreateMethods {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    //隐式转换
    val ints = Seq(1, 2, 3, 5)
    //1.使用基本序列创建ds
    val ds1: Dataset[Int] = ints.toDS()
    ds1.show()
    //2.使用样例类序列创建ds
    val ds2: Dataset[User] = Seq(User("zs", 19)).toDS()
    ds2.show()
    //3.直接创建ds
    val ds3: Dataset[User] = spark.createDataset(List(User("zs", 16), User("lisi", 18)))
    ds3.show()

    //4.df转换
    val df2: DataFrame = spark.createDataFrame(List(User("hello", 2), User("spark", 2)))
    val ds4: Dataset[User] = df2.as[User]
    ds4.show()

    /*
    总结:创建ds的三种方式
    1.RDD.toDS转换
    2.DF.as[对象类型]转换
    3.createDataset直接创建
      3.1:通过基本数据类型直接创建,列名不会被自动识别,会用_1,_2替代
      3.2:通过对象创建可以自动识别列名
     */


    /*
    show(1000,false)
    1000:展示1000行
    false:列数据不进行截取
     */
    spark.stop()
  }
}

case class User(val name:String,val age:Int)
