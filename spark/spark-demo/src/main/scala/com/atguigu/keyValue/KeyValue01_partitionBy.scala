package com.atguigu.keyValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

object KeyValue01_partitionBy {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[(Any, String)] = sc.makeRDD(Array((1, "aaa"), (2, "bbb"), (3, "ccc"), (4, "ddd"),("5","eeee")), 3)
    //重新分区,可以扩大也可以缩小
    rdd.partitionBy(new MyPartitioner(3)).mapPartitionsWithIndex((a, b) => b.map(i => (a, i))).collect().foreach(print)
    sc.stop()
  }
}

class MyPartitioner(val nums: Int) extends Partitioner {
  override def numPartitions: Int = {
    nums
  }

  override def getPartition(key: Any): Int = {
    if (key.isInstanceOf[Int]) {
      val keyInt: Int = key.asInstanceOf[Int]
      if (keyInt % 2 == 0) {
        0
      }else 1
    }else
      2
  }

  //最好加一个判断分区器是否是重复的
  override def equals(other: Any): Boolean = other match {
    case h: HashPartitioner =>
      h.numPartitions == numPartitions
    case _ =>
      false
  }
}
