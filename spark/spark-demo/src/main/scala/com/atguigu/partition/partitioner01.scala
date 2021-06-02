package com.atguigu.partition

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object partitioner01 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val value: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8),3)
    println(value.partitioner)
    //不改变分区器
    val value1: RDD[(Int, Int)] = value.map(i => (i, 1))
    println(value1.partitioner)
    //改变了分区器
    val value2: RDD[(Int, Int)] = value1.partitionBy(new HashPartitioner(3))
//    val value2: RDD[(Int, Int)] = value1.repartition(4)
    println(value2.partitioner)
    /*
    1.每个RDD都有分区器,k-v类型的RDD有具体的分区器,非k-v类型的RDD分区器为None
    2.分区器有 hash分区器(默认),range分区器,和自定义分区器
    3.只有partitionBy 这种传入分区器的才能更改分区器
     */
    //k-v RDD默认也没有分区器
    println("============k-vRDD 也没有自带的分区器=============")
    val value3: RDD[(Int, Int)] = sc.makeRDD(List((1, 2), (1, 2), (1, 2), (1, 2),(2,3),(3,4)),1)
    println("k-v"+value3.partitioner)
    val value4: RDD[(Int, Int)] = value3.repartition(5)
    println(value4.partitioner)
    val value5: RDD[(Int, Int)] = value3.sortByKey(true,2)
    value5.mapPartitionsWithIndex((a, b) => b.map(i => (a, i))).collect().foreach(print)
    println(value5.partitioner)
    val value6: RDD[(Int, Int)] = value3.sortBy(i=>i._1,true,2)
    value6.mapPartitionsWithIndex((a, b) => b.map(i => (a, i))).collect().foreach(print)
    println(value6.partitioner)
    sc.stop()
  }
}
