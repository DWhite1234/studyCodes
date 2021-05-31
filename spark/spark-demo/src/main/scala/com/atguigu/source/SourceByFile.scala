package com.atguigu.source

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SourceByFile {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val value: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\1.txt")
    /*
    1.不指定分区
      def defaultMinPartitions: Int = math.min(defaultParallelism, 2)

      defaultParallelism:scheduler.conf.getInt("spark.default.parallelism", totalCores)

      默认分区为最大核心线程数,读取文件默认取2个分区
     */

    val value1: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\2.txt", 3)
    value1.saveAsTextFile("output2")
    /*
    1.指定分区
      package org.apache.hadoop.mapred(1.x的hadoop切片方法)

      1.totalSize计算文件总大小
      long totalSize = 0;
      totalSize += file.getLen();

      2.goalSize计算目标大小
      long goalSize = totalSize / (numSplits == 0 ? 1 : numSplits);


      3.计算切片大小                                    不设置默认为1
      val splitSize: Long = computeSplitSize(goalSize, minSize, blockSize)
      Math.max(minSize, Math.min(goalSize, blockSize));
      ==>近似于
      splitSize=goalSize

      文件大小/splitSize+切片逻辑1.1倍

      指定分区,走的是hadoop1.x的读取文件,和文件切片的方法


      注意：
      1.getSplits文件返回的是切片规划，真正读取是在compute方法中创建LineRecordReader读取的，
      有两个关键变量： start = split.getStart()	   end = start + split.getLength
      2. Spark读取文件，采用的是hadoop的方式读取，所以一行一行读取，跟字节数没有关系
      3.数据读取位置计算是以偏移量为单位来进行计算的。
      4.数据分区的偏移量范围的计算
      0 => [0,3]         1@@     012        0 => 1,2
      1 => [3,6]         2@@     345        1 => 3
      2 => [6,9]         3@@     678        2 => 4
      3 => [9,10]        4       9          3 => 无
     */
    sc.stop()
  }
}
