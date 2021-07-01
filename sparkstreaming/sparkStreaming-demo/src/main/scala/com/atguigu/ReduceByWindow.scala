package com.atguigu

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ReduceByWindow {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("ReduceByKeyAndWindow")
    val ssc = new StreamingContext(conf, Seconds(3))

    val socketDStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop104", 9999)

    val newDStream: DStream[String] = socketDStream.reduceByWindow(_ + " " + _, Seconds(9), Seconds(6))
    newDStream.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()


    ssc.start()
    ssc.awaitTermination()
  }
}
