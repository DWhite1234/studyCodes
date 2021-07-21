package com.atguigu.analyse

import com.alibaba.fastjson.JSON
import com.atguigu.FinalVariable
import com.atguigu.Utils.MyKafkaUtils
import com.atguigu.bean.StartUpLog
import com.atguigu.handler.DAU_Handler
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.{SparkConf, rdd}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.phoenix.spark._
import org.apache.spark.rdd.RDD

import java.text.SimpleDateFormat
import java.util.Date

object DAU_Analyse {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("DAU_Analyse")
    val ssc = new StreamingContext(conf, Seconds(3))

    //读取kafka数据
    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtils.getKafkaSource(Array(FinalVariable.GMALL_STARTUP), ssc)

    //转换数据结构,将数据包装成类
    val format = new SimpleDateFormat("yyyy-MM-dd HH")
    val startUpLog: DStream[StartUpLog] = kafkaDStream.mapPartitions(rdd => {
      rdd.map(
        value => {
          val startUpLog: StartUpLog = JSON.parseObject(value.value(), classOf[StartUpLog])
          //补充字段
          val dateTime: String = format.format(new Date(startUpLog.ts))
          startUpLog.logDate = dateTime.split(" ")(0)
          startUpLog.logHour = dateTime.split(" ")(1)
          startUpLog
        }
      )
    })
    //1.批次间去重
    val filterMids: DStream[((String, String), StartUpLog)] = DAU_Handler.filterByMid(startUpLog)
    //2.批次内去重
    val filterInner: DStream[((String, String), StartUpLog)] = DAU_Handler.filterByInnerMid(filterMids)
    //3.批次间去重后写入redis
    DAU_Handler.writeToRedis(filterInner)
    //4.写入hbase
    filterInner.foreachRDD(
      rdd=>{
        val value: RDD[StartUpLog] = rdd.map(_._2)
        value.foreach(println(_))
        value.saveToPhoenix(
        "GMALL2021_DAU",
        Seq("MID", "UID", "APPID", "AREA", "OS", "CH", "TYPE", "VS", "LOGDATE", "LOGHOUR", "TS"),
        HBaseConfiguration.create,
        Some("hadoop104,hadoop105,hadoop106:2181")
        )
      }
    )

    ssc.start()
    ssc.awaitTermination()
  }
}
