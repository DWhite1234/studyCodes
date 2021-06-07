package com.atguigu.df

import com.atguigu.ds
import com.atguigu.ds.User
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object dfCreateMethods {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    //rdd 转换df,不是指列名
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("hello", 1), ("spark", 1)))
    rdd.toDF().show()

    //指定列名转换
    rdd.toDF("name","age").show()


    //基本数据类型直接创建df
    val df: DataFrame = spark.createDataFrame(List(("hello", 2), ("spark", 2)))
    df.toDF("name","age")
    df.show()
    //对象直接创建df
    val df2: DataFrame = spark.createDataFrame(List(User("hello", 2), User("spark", 2)))
    df2.show()

    //ds转换
    val ds: Dataset[User] = spark.createDataset(List(User("hello", 3), User("spark", 3)))
    val df3: DataFrame = ds.toDF()
    df3.show()
    /*
   总结:DF的三种创建方式
   1.RDD转换
   2.createDataFrame直接创建
   3.DS.toDF
    */

    spark.stop()
  }
}

