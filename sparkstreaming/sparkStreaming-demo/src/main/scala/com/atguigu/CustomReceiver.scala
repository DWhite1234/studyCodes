package com.atguigu

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.receiver.Receiver

import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.net.Socket

class CustomReceiver extends Receiver[String](StorageLevel.MEMORY_ONLY) {

  var socket: Socket = _

  override def onStart(): Unit = {
    socket = new Socket("hadoop104", 9999)


    new Thread("my receiver") {
      override def run() = {
        receiver()
      }
    }.start()
  }

  override def onStop(): Unit = {
    if (socket != null) {
      socket.close()
      socket = null
    }
  }

  def receiver() = {
    val inputStream: InputStream = socket.getInputStream
    val reader = new BufferedReader(new InputStreamReader(inputStream))
    var len: String = reader.readLine()
    while (!isStopped() && len != null) {
      store(len)
      len = reader.readLine()
    }
  }
}

object test{
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("myReceiver")
    val ssc = new StreamingContext(conf, Seconds(3))

    val value: ReceiverInputDStream[String] = ssc.receiverStream(new CustomReceiver())

    value.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    ssc.start()
    ssc.awaitTermination()
  }
}
