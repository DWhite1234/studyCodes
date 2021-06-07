package com.atguigu.realExamples

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Example03 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    /*
    求页面单跳转化率
    页面单跳转化率: 1-2 /1   即从首页到详情页/首页  数量百分比

    val list = List(1,2,3,4,5,6,7)
    重点:怎么实现 1-2,2-3,3-4这样的数据类型
    解决:
      1.scala 的 tail函数 可以获取除去第一个的 剩余所有值
      2.通过zip函数 拉链,进行两两配对,匹配不上的会自动去除
    例如:
      list2 = list.tail ==> List(2,3,4,5,6,7)
      list.zip(list2) ==> List((1-2),(2-3),(3-4)...)
     */

    val dataSource: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\user_visit_action.txt")
    val initList = List("1", "2", "3", "4", "5", "6", "7")
    val zipList: List[String] = initList.zip(initList.tail).map(i => i._1 + "-" + i._2)
    val ids: Broadcast[List[String]] = sc.broadcast(initList)
    val userActionRdd: RDD[UserAction] = dataSource.map(
      i => {
        val list: Array[String] = i.split("_")
        //利用apply创建样例类
        UserAction(
          list(0),
          list(1),
          list(2),
          list(3),
          list(4),
          list(5),
          list(6),
          list(7),
          list(8),
          list(9),
          list(10),
          list(11),
          list(12)
        )
      }
    )
    //1.求各个分母的值
    val pageNums: Array[(String, Int)] = userActionRdd
      //过滤不符合的数据
      .filter(action => ids.value.init.contains(action.page_id))
      //转换数据结构
      .map(action => (action.page_id, 1))
      //聚合
      .reduceByKey(_ + _)
      //不收集的结果是 RDD[String,Int] 无法转成map,收集的结果是Array[(String, Int)] 可以转成RDD
      .collect()

    //2.求各个分子的值
    val value: RDD[(String, List[String])] = userActionRdd
      //转换数据结构 UserAction ==> (use.session_id, (use.page_id, use.action_time))
      .map(use => (use.session_id, (use.page_id, use.action_time)))
      //分组
      .groupByKey()
      //对v 进行处理
      .mapValues(i => {
        val sort: List[(String, String)] = i.toList.sortBy(_._2)
        val list: List[String] = sort.map(_._1)
        val zList: List[(String, String)] = list.zip(list.tail)
        val pageZipList: List[String] = zList.map(i => i._1 + "-" + i._2)
        val list1: List[String] = pageZipList.filter(
          i => {
            zipList.contains(i)
          }
        )
        list1
      })
    val value1: RDD[List[String]] = value.map(_._2)

    val value2: RDD[String] = value1.flatMap(i => i)

    val value3: RDD[(String, Int)] = value2.map((_, 1))
    val value4: RDD[(String, Int)] = value3.reduceByKey(_ + _)

    //3.计算
    val value5: RDD[(String, Double)] = value4.map(
      i => {
        val strings: Array[String] = i._1.split("-")
        val i1: Int = pageNums.toMap.getOrElse(strings(0), 1)
        (i._1, i._2.toDouble / i1)
      }
    )
    val result: Array[(String, Double)] = value5.collect()
    sc.stop()
  }
}
