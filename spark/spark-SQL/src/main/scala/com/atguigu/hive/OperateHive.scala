package com.atguigu.hive

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object OperateHive {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    //添加外部hive的支持
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()
    //连接外部hive,需要把hive的hive-site.xml复制到resources下
    spark.sql("select * from sparttest").show()

    spark.stop()
  }
}
