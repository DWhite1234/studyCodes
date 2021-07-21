package com.atguigu.handler

import com.atguigu.bean.StartUpLog
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import redis.clients.jedis.Jedis


import java.text.SimpleDateFormat
import java.util

object DAU_Handler {

  val format = new SimpleDateFormat("yyyy-MM-dd")

  def filterByInnerMid(filterMids: DStream[((String, String), StartUpLog)]) = {
    val value1: DStream[((String, String), StartUpLog)] = filterMids.map(
      value => {
        ((value._1._1, value._2.logDate), value._2)
      }
    )
    val value2: DStream[((String, String), Iterable[StartUpLog])] = value1.groupByKey()
    val value3: DStream[((String, String), List[StartUpLog])] = value2.mapValues(
      value => {
        value.toList
          .sortBy(_.ts)(Ordering[Long].reverse)
          .take(1)
      }
    )
    val value4: DStream[((String, String), StartUpLog)] = value3.map(
      value => {
        (value._1, value._2(0))
      }
    )
    value4
  }



  def filterByMid(startUpLog: DStream[StartUpLog]) = {
    val startUpLogToKV: DStream[((String, String), StartUpLog)] = startUpLog.map(value => {
      ((value.mid, value.logDate), value)
    })

    startUpLogToKV.transform(
      rdd => {
        rdd.mapPartitions(
          value => {
            val jedis = new Jedis("hadoop104", 6379)
            val filterMids: Iterator[((String, String), StartUpLog)] = value.filter(
              kv => {
                val mid: String = "mid:" + format.format(kv._2.ts)
                val allMid: util.Set[String] = jedis.smembers(mid)
                !allMid.contains(kv._2.mid)
              }
            )
            jedis.close()
            filterMids
          }
        )
      }
    )
  }


  /**
   * 查redis 存在的mid
   *
   * @param key
   * @return
   */

  def readByRedis(key: String) = {
    val jedis = new Jedis("hadoop104", 6379)
    val kList: util.Set[String] = jedis.smembers(key)
    jedis.close()
    kList
  }

  /**
   * 去重后写入redis
   *
   * @param startUpLog
   */
  def writeToRedis(startUpLog: DStream[((String, String), StartUpLog)]) = {
    startUpLog.foreachRDD(
      rdd => {
        rdd.foreachPartition(
          value => {
            val jedis = new Jedis("hadoop104", 6379)
            value.foreach(
              kv => {
                val mid: String = "mid:" + format.format(kv._2.ts)
                jedis.sadd(mid,kv._2.mid)
              }
            )
            jedis.close()
          }
        )
      }
    )
  }
}
