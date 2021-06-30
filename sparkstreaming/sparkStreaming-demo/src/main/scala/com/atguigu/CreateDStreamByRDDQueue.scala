package com.atguigu

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable


object CreateDStreamByRDDQueue {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("CreateDStreamByRDDQueue")
    val ssc = new StreamingContext(conf, Seconds(3))

    val queue = new mutable.Queue[RDD[Int]]
    /*
      queueStream(queue,true)
      第一个参数为队列参数
      第二个参数为oneAtATime 默认为true
      oneAtATime = true 默认，一次读取队列里面的一个数据
      oneAtATime = false， 按照设定的批次时间，读取队列里面数据
     */
    val inputDStream: InputDStream[Int] = ssc.queueStream(queue,false)

    inputDStream.reduce(_+_).print()

    ssc.start()
    for (elem <- (1 to 6)) {
      queue+=ssc.sparkContext.makeRDD((1 to 5))
      Thread.sleep(1000)
    }

    ssc.awaitTermination()
  }
}
