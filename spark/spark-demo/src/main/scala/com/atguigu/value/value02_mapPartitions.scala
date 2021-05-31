package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value02_mapPartitions {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(1 to 4, 2)

    //map是一次处理一条数据,mapPartitions是一次处理一批数据,在这一批数据中每个数据调用map
    //缺点:一次导入分区所有数据,内存释放不及时,容易导致OOM
    val value: RDD[(Int, Int)] = rdd.mapPartitions(iter => iter.map((i => (i, 1))))
    value.collect().foreach(println)
    sc.stop()
  }
}
