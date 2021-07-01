package com.atguigu

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import redis.clients.jedis.{Jedis, JedisPool}

object ForeachRDD {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("ForeachRDD")
    val ssc = new StreamingContext(conf, Seconds(3))

    val socketDStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop104", 9999)

    socketDStream.foreachRDD(
      rdd => {
        val tup: RDD[(String, Int)] = rdd.flatMap(_.split(" "))
          .map((_, 1))
        tup.foreachPartition(
          value =>{
            val jedis = new Jedis("hadoop104", 6379)
            value.foreach(
              i=>{
                jedis.set(i._1,i._2+"")
              }
            )
            jedis.close()
          }
        )
      }
    )
    socketDStream.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
