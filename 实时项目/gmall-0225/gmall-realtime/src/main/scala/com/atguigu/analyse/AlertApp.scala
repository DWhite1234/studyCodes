package com.atguigu.analyse

import com.alibaba.fastjson.JSON
import com.atguigu.FinalVariable
import com.atguigu.Utils.{MyEsUtil, MyKafkaUtils}
import com.atguigu.bean.{CouponAlertInfo, EventLog}
import com.ibm.icu.text.SimpleDateFormat
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.util.control.Breaks._
import java.util
import java.util.Date

object AlertApp {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("AlertApp")
    val ssc = new StreamingContext(conf, Seconds(5))

    //读取kafka
    val KafkaDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtils.getKafkaSource(Array(FinalVariable.KAFKA_TOPIC_EVENT), ssc)

    //转成样例类
    val format = new SimpleDateFormat("yyyy-MM-dd HH")
    val eventLogDStream: DStream[(String, EventLog)] = KafkaDStream.mapPartitions(rdd => {
      rdd.map(value => {
        val eventLog: EventLog = JSON.parseObject(value.value(), classOf[EventLog])
        val dateTime: String = format.format(new Date(eventLog.ts))
        eventLog.logDate = dateTime.split(" ")(0)
        eventLog.logHour = dateTime.split(" ")(1)
        (eventLog.mid, eventLog)
      })
    })

    //根据条件过滤
    //开窗
    val eventLogWindow: DStream[(String, EventLog)] = eventLogDStream.window(Seconds(300), Seconds(5))
    //分组
    val couponAlertDStream: DStream[(String, Iterable[EventLog])] = eventLogWindow.groupByKey()
    //去重
    val resultDStream: DStream[(Boolean, CouponAlertInfo)] = couponAlertDStream.mapPartitions(rdd => {
      rdd.map(value => {
        //领取优惠券的uid
        val uids = new util.HashSet[String]()
        //涉及的商品
        val items = new util.HashSet[String]()
        //发生过的行为
        val evids = new util.ArrayList[String]()

        //根据需求过滤
        breakable(
          for (elem <- value._2) {
            evids.add(elem.evid)
            if ("clickItem".equals(elem.evid)) {
              break()
            } else if ("coupon".equals(elem.evid)) {
              uids.add(elem.uid)
              items.add(elem.itemid)
            }
          }
        )

        //判断,返回预警日志
        (uids.size() >= 3, new CouponAlertInfo(value._1, uids, items, evids, System.currentTimeMillis()))
      })
    })

    val value: DStream[CouponAlertInfo] = resultDStream.filter(_._1).map(_._2)
    value.print()

    value.foreachRDD(rdd => {
      rdd.foreachPartition(part => {
        val res= part.toList.map(log => {
          (log.mid + ":" + log.ts / 1000 / 60,log)
        })
        MyEsUtil.insertBulk(FinalVariable.ES_ALERT,res)
      })
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
