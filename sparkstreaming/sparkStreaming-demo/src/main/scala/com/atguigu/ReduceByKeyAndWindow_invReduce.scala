package com.atguigu

package com.atguigu

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

object ReduceByKeyAndWindow_invReduce {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("ReduceByKeyAndWindow")
    val ssc = new StreamingContext(conf, Seconds(3))
    ssc.checkpoint("ck")
    val socketDStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop104", 9999)

    val kvDStream: DStream[(String, Int)] = socketDStream.flatMap(_.split(" "))
      .map((_, 1))

    kvDStream.reduceByKeyAndWindow(
      (a: Int, b: Int) => a + b,
      (a: Int, b: Int) => a + b,
      Seconds(9),
      Seconds(6)
    )
      .print()


    ssc.start()
    ssc.awaitTermination()
  }
}
