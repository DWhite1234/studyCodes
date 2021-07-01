package com.atguigu

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TestTransform {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("TestTransform")
    val ssc = new StreamingContext(conf, Seconds(3))

    val socketDStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop104", 9999)

    socketDStream.transform(rdd=>{
      rdd.flatMap(_.split(" "))
        .map((_,1))
        .reduceByKey(_+_)
    }).print()

    ssc.start()
    ssc.awaitTermination()
  }
}
