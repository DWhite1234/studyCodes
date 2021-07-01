package com.atguigu

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Window{
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WindowOperations")

    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))

    val socketDStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop104", 9999)

    val windowDStream: DStream[String] = socketDStream.window(Seconds(9), Seconds(3))

    windowDStream.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    ssc.start()
    ssc.awaitTermination()
  }
}
