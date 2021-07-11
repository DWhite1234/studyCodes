package com.atguigu.analyse

import com.alibaba.fastjson.JSON
import com.atguigu.FinalVariable
import com.atguigu.Utils.MyKafkaUtils
import com.atguigu.bean.UserInfo
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.json4s.jackson.Serialization
import redis.clients.jedis.Jedis

object UserApp {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("UserApp")
    val ssc = new StreamingContext(conf, Seconds(3))

    val userInfoDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtils.getKafkaSource(Array(FinalVariable.KAFKA_TOPIC_USER), ssc)

    userInfoDStream.foreachRDD(rdd=>{
      rdd.foreachPartition(iter=>{
        val jedis = new Jedis("hadoop104", 6379)
        iter.foreach(value=>{
          implicit val formats = org.json4s.DefaultFormats
          val userInfo: UserInfo = JSON.parseObject(value.value(), classOf[UserInfo])
          val user: String = Serialization.write(userInfo)
          jedis.set(userInfo.id,user)
          println("写出成功")
        })
        jedis.close()
      })
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
