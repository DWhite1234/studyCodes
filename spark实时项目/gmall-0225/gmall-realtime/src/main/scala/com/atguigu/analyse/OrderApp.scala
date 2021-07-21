package com.atguigu.analyse

import com.alibaba.fastjson.JSON
import com.atguigu.FinalVariable
import com.atguigu.Utils.{MyEsUtil, MyKafkaUtils}
import com.atguigu.bean.{OrderDetail, OrderInfo, SaleDetail, UserInfo}
import com.ibm.icu.text.SimpleDateFormat
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.SparkConf
import org.json4s.jackson.Serialization
import redis.clients.jedis.Jedis

import java.util
import java.util.Date
import scala.collection.JavaConverters.asScalaBufferConverter

object OrderApp {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("OrderApp")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))

    val orderKafkaDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtils.getKafkaSource(Array(FinalVariable.KAFKA_TOPIC_ORDER), ssc)
    val orderDetailKafkaDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtils.getKafkaSource(Array(FinalVariable.KAFKA_TOPIC_ORDER_DETAIL), ssc)

    val orderInfoClass: DStream[(String, OrderInfo)] = orderKafkaDStream.mapPartitions(rdd => {
      rdd.map(value => {
        val orderInfo: OrderInfo = JSON.parseObject(value.value(), classOf[OrderInfo])
        orderInfo.create_date = orderInfo.create_time.split(" ")(0)
        orderInfo.create_hour = orderInfo.create_time.split(" ")(1).split(":")(0)
        (orderInfo.id, orderInfo)
      })
    })

    val orderDetailClass: DStream[(String, OrderDetail)] = orderDetailKafkaDStream.mapPartitions(rdd => {
      rdd.map(value => {
        val detail: OrderDetail = JSON.parseObject(value.value(), classOf[OrderDetail])
        (detail.order_id, detail)
      })
    })

    val fullJoinDStream: DStream[(String, (Option[OrderInfo], Option[OrderDetail]))] = orderInfoClass.fullOuterJoin(orderDetailClass)


    /*
    full out join后的数据格式
    (1,  -- 双流join的条件 在这里指order_id
      (Some
          (OrderInfo
              (
                  1,9,cfvMxa,cUrXPfjDpPeefncvXAIO,13641456451,1,2,2,,421.0,,bptZQhmOhwKmfPIWJJdx,2021-07-13 18:17:43,,,,9262435675,,2021-07-13,18)
              ),  -- some有值代表orderInfo 表 order_id为1的 数据
      None -- None 代表 Order_detail没有order_id为1的数据
      )
  )

  总结:如果order_id  join上了  前后则都是some

     */
    /*
    //查看full out join的数据结构
    fullJoinDStream.foreachRDD(rdd=>{
      rdd.foreachPartition(value=>{
        value.foreach(println(_))
      })
    })
     */

    fullJoinDStream.foreachRDD(rdd=>{
      rdd.foreachPartition(value=>{
        value.foreach(println(_))
      })
    })

    val fullOutJoinResult: DStream[SaleDetail] = fullJoinDStream.mapPartitions(rdd => {
      val jedis = new Jedis("hadoop104", 6379)
      //创建数组 存放关联上的数据
      val details = new util.ArrayList[SaleDetail]()
      rdd.foreach { case (order_id, (orderOpt, detailOpt)) => {

        implicit val formats = org.json4s.DefaultFormats

        //判断order是否匹配上数据
        if (orderOpt.isDefined) {
          //orderInfo
          val orderInfo: OrderInfo = orderOpt.get
          val orderKey = s"orderId:${orderInfo.id}"
          //判断tetail是否full join成功
          if (detailOpt.isDefined) {
            //两者都full join 上,将数据添加在关联数组上
            val detailInfo: OrderDetail = detailOpt.get
            val saleDetail = new SaleDetail(orderInfo, detailInfo)
            details.add(saleDetail)
          } else {
            //没有full join 上只有 order有数据,先写入缓存
            val orderInfoSeriza: String = Serialization.write(orderInfo)
            jedis.set(orderKey, orderInfoSeriza)
            jedis.expire(orderKey,30)
            //读取detail的缓存
            val readFormRedis: String = jedis.get(orderKey)
            //不为null则读到了数据
            if (readFormRedis != null) {
              val detail: OrderDetail = JSON.parseObject(readFormRedis, classOf[OrderDetail])
              val saleDetail = new SaleDetail(orderInfo, detail)
              details.add(saleDetail)
            }
          }
        } else {
          val detailInfo = detailOpt.get
          val detailKey = s"detailId:${detailInfo.order_id}"

          //先读取redis
          val detailFromRedis: String = jedis.get(detailKey)
          if (detailFromRedis != null) {
            //读取到了数据
            val orderIno: OrderInfo = JSON.parseObject(detailFromRedis, classOf[OrderInfo])
            val saleDetail = new SaleDetail(orderIno, detailInfo)
            details.add(saleDetail)
          } else {
            //读不到数据则把自身缓存下来
            val detail: String = Serialization.write(detailInfo)
            jedis.sadd(detailKey, detail)
            jedis.expire(detailKey, 30)
          }
        }
      }
      }
      jedis.close()
      details.asScala.iterator
    })

    //读取redis用户信息,补全用户信息
    val result: DStream[SaleDetail] = fullOutJoinResult.mapPartitions(rdd => {
      val jedis = new Jedis("hadoop104", 6379)
      rdd.map(saleDetail => {
        val readFromRedis: String = jedis.get(saleDetail.user_id)
        val userInfo: UserInfo = JSON.parseObject(readFromRedis, classOf[UserInfo])
        saleDetail.mergeUserInfo(userInfo)
        saleDetail
      })
    })

    //把结果写入er
    result.foreachRDD(rdd=>{
      val list: List[SaleDetail] = rdd.collect().toList
      val result: List[(String, SaleDetail)] = list.map(saleDetail => (saleDetail.order_detail_id, saleDetail))
      val format = new SimpleDateFormat("yyyy-MM-dd")
      val date: String = format.format(new Date())
      MyEsUtil.insertBulk(FinalVariable.ES_SALE_DETAIL+"_"+date,result)
    })


    ssc.start()
    ssc.awaitTermination()

  }
}
