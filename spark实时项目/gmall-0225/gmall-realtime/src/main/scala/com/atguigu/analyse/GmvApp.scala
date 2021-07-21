package com.atguigu.analyse

import com.alibaba.fastjson.{JSON, JSONObject}
import com.atguigu.FinalVariable
import com.atguigu.Utils.MyKafkaUtils
import com.atguigu.bean.OrderInfo
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.phoenix.spark._

object GmvApp {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("GmvApp")
    val ssc = new StreamingContext(conf, Seconds(5))

    val kaflaDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtils.getKafkaSource(Array(FinalVariable.KAFKA_TOPIC_ORDER), ssc)

    val orderInfoDStream: DStream[OrderInfo] = kaflaDStream.mapPartitions(rdd => {
      rdd.map(value => {
        val orderInfo: OrderInfo = JSON.parseObject(value.value(), classOf[OrderInfo])
        //补充字段
        orderInfo.create_date = orderInfo.create_time.split(" ")(0)
        orderInfo.create_hour = orderInfo.create_time.split(" ")(1).split(":")(0)
        orderInfo
      })
    })

    orderInfoDStream.foreachRDD(rdd => {
      rdd.saveToPhoenix(
        "gmall2021_order_info",
        Seq("ID", "PROVINCE_ID", "CONSIGNEE", "ORDER_COMMENT", "CONSIGNEE_TEL", "ORDER_STATUS", "PAYMENT_WAY", "USER_ID", "IMG_URL", "TOTAL_AMOUNT", "EXPIRE_TIME", "DELIVERY_ADDRESS", "CREATE_TIME", "OPERATE_TIME", "TRACKING_NO", "PARENT_ORDER_ID", "OUT_TRADE_NO", "TRADE_BODY", "CREATE_DATE", "CREATE_HOUR"),
        HBaseConfiguration.create(),
        Some("hadoop104,hadoop105,hadoop106:2181")
      )
    })

    ssc.start()
    ssc.awaitTermination()

  }
}
