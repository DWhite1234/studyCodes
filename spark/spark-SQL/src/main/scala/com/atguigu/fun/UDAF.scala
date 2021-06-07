package com.atguigu.fun

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{DataFrame, Encoder, Encoders, SparkSession, functions}

object UDAF {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

    val df: DataFrame = spark.read.json("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")
    df.createTempView("User")
    //注册自定义UDAF函数,
    spark.udf.register("myAvg", functions.udaf(new MyAvg()))
    spark.sql("select myAvg(age) from User").show()
    spark.stop()
  }
}

case class Buff(var sum: Long, var count: Long)

class MyAvg extends Aggregator[Long, Buff, Double] {
  //初始化缓冲对象
  override def zero: Buff = Buff(0, 0)

  //将输入数据和缓冲对象聚合 类似累加器的add方法
  override def reduce(buff: Buff, a: Long): Buff = {
    buff.sum += a
    buff.count += 1
    buff
  }
  //将多个缓冲对象合并,类似于累加器的分区间聚合方法
  override def merge(b1: Buff, b2: Buff): Buff = {
    b1.sum+=b2.sum
    b1.count+=b2.count
    b1
  }

  //输出最终结果
  override def finish(reduction: Buff): Double = {
    reduction.sum.toDouble/reduction.count
  }

  //自定义类型的序列化 product
  override def bufferEncoder: Encoder[Buff] = Encoders.product

  //序列化类型
  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}

