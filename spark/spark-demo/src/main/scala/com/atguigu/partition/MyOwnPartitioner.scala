package com.atguigu.partition

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

object MyOwnPartitioner {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val value: RDD[(Int, Int)] = sc.makeRDD(List((1, 2), (1, 2), (1, 2), (1, 2)))
    val value1: RDD[(Int, Int)] = value.partitionBy(new MyPartitioner(3))
    println(value1.partitioner)
    sc.stop()
  }
}

/*
自定义分区器

1.继承Partitioner抽象类
2.重写numPartitions方法设置分区数
3.重写getPartition方法定义分区逻辑
4.推荐重写equals判断传入的分区器时候重复
 */
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
