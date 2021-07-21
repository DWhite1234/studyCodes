package com.atguigu.Utils

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.{KafkaUtils, LocationStrategies}

import java.util.Properties

object MyKafkaUtils {

  private val properties: Properties = LoadProperties.loadProperties()

  val map: Map[String, Object] = Map[String, Object](
    "bootstrap.servers" -> properties.getProperty("kafka.broker.list"),
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> properties.getProperty("group_id"),
    "auto.offset.reset" -> properties.getProperty("offset_reset"),
    "enable.auto.commit" -> (true: java.lang.Boolean)
  )

  def getKafkaSource(topics: Array[String], ssc: StreamingContext) = {
    val DStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      Subscribe[String, String](topics, map)
    )
    DStream
  }
}
