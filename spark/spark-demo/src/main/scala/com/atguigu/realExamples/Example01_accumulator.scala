package com.atguigu.realExamples

import org.apache.spark.rdd.RDD
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.{immutable, mutable}

object Example01_accumulator {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val dataSource: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\user_visit_action.txt")
    //创建自定义累加器
    val acc = new MyTopAccumulator
    //注册自定义累加器
    sc.register(acc)
    //将数据注入对象中
    val value: RDD[UserAction] = dataSource.map(
      i => {
        val data: Array[String] = i.split("_")
        UserAction(
          data(0),
          data(1),
          data(2),
          data(3),
          data(4),
          data(5),
          data(6),
          data(7),
          data(8),
          data(9),
          data(10),
          data(11),
          data(12)
        )
      })
    //使用累加器
    value.foreach(acc.add(_))
    val map: mutable.Map[(String, String), Int] = acc.value
    //map类型而不是算子,指定按照key分组
    val map1: Map[String, mutable.Map[(String, String), Int]] = map.groupBy(
      i => i._1._1
    )
    //封装成输出对象
    val map2: immutable.Iterable[ExampleTop10] = map1.map {
      case (id, map) => {
        //避免空值,使用getOrElse
        val click: Long = map(id, "click")
        val order: Long = map(id, "order")
        val pay: Long = map(id, "pay")

        ExampleTop10(id, click, order, pay)
      }
    }
    //(重点)指定排序字段 ,排序字段可以自由组合,这里使用的排序是函数,不是算子
    val map3: List[ExampleTop10] = map2.toList.sortBy(i => (i.clickCount, i.orderCount, i.payCount))(Ordering[(Long, Long, Long)].reverse)
    map3.take(10).foreach(println)
    sc.stop()
  }
}

class MyTopAccumulator extends AccumulatorV2[UserAction, mutable.Map[(String, String), Int]] {
  var map: mutable.Map[(String, String), Int] = mutable.Map[(String, String), Int]()

  override def isZero: Boolean = map.isEmpty

  override def copy(): AccumulatorV2[UserAction, mutable.Map[(String, String), Int]] = new MyTopAccumulator

  override def reset(): Unit = map.clear()

  //自定义分区内,数据添加逻辑
  override def add(action: UserAction): Unit = {
    if (action.click_category_id != "-1") {
      map((action.click_category_id, "click")) = map.getOrElse((action.click_category_id, "click"), 0) + 1
    } else if (action.order_category_ids != "null") {
      val list: Array[String] = action.order_category_ids.split(",")
      list.foreach(
        id => {
          map((id, "order")) = map.getOrElse((id, "order"), 0) + 1
        }
      )
    } else if (action.pay_category_ids != "null") {
      val list: Array[String] = action.pay_category_ids.split(",")
      list.foreach(
        id => {
          map((id, "pay")) = map.getOrElse((id, "pay"), 0) + 1
        }
      )
    }
  }

  //自定义分区间合并逻辑
  override def merge(other: AccumulatorV2[UserAction, mutable.Map[(String, String), Int]]): Unit = {
    val newMap: mutable.Map[(String, String), Int] = other.value
    newMap.toList.foreach(
      id => {
        map(id._1) = map.getOrElse(id._1, 0) + id._2
      }
    )
  }

  override def value: mutable.Map[(String, String), Int] = map
}
