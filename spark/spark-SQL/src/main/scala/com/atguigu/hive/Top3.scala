package com.atguigu.hive

import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Encoder, Encoders, SparkSession, functions}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Top3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()
    spark.udf.register("city_remark", functions.udaf(new city_remark()))
    spark.sql(
      """
        |select
        |    t4.area,
        |    t4.product_name,
        |    t4.totalCount,
        |    t4.remark
        |from
        |(
        |    select
        |        t3.area,
        |        t3.product_name,
        |        t3.totalCount,
        |        t3.remark,
        |        rank() over(partition by t3.area order by t3.totalCount desc) rk
        |    from
        |    (
        |        select
        |            t2.area,
        |            t2.product_name,
        |            count(*) totalCount,
        |            city_remark(t2.city_name) remark
        |        from
        |        (
        |            select
        |                c.area,
        |                c.city_name,
        |                p.product_name
        |            from
        |                (select * from user_visit_action where click_product_id != -1) t1
        |            join city_info c
        |            on t1.city_id = c.city_id
        |            join product_info p
        |            on t1.click_product_id = p.product_id
        |        )t2
        |        group by t2.area,t2.product_name
        |    )t3
        |)t4
        |where t4.rk <= 3
        |""".stripMargin).show(1000, false)

    spark.stop()
  }
}

case class Buffer(var totalCount: Long, var map: mutable.Map[String, Long])

class city_remark extends Aggregator[String, Buffer, String] {
  override def zero: Buffer = Buffer(0L, mutable.Map())

  override def reduce(buffer: Buffer, a: String): Buffer = {
    buffer.map(a) = buffer.map.getOrElse(a, 0L) + 1
    buffer.totalCount += 1
    buffer
  }

  override def merge(b1: Buffer, b2: Buffer): Buffer = {
    b2.map.foreach(
      i => {
        b1.map(i._1) = b1.map.getOrElse(i._1, 0L) + i._2
      }
    )
    b1.totalCount += b2.totalCount
    b1
  }

  override def finish(buffer: Buffer): String = {
    //最后输出的listBuffer
    var outList: ListBuffer[(String, String)] = ListBuffer()

    //排序取前2
    val value2: List[(String, Long)] = buffer.map
      .toList
      .sortBy(_._2)(Ordering[Long].reverse)
      .take(2)
    //计算比值
    var sum = 0L
    value2.foreach(
      i => {
        val res: Long = i._2 * 100 / buffer.totalCount
        outList.append((i._1, res + "%"))
        sum += res
      }
    )
    //大于两个的区域添加其他
    if (buffer.map.size > 2) {
      outList.append(("其他", (100 - sum) + "%"))
    }
    outList.mkString(",")
  }

  override def bufferEncoder: Encoder[Buffer] = Encoders.product

  override def outputEncoder: Encoder[String] = Encoders.STRING
}


