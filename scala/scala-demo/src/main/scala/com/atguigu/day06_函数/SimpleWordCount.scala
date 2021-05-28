package com.atguigu.day06_函数

import scala.collection.MapView

object SimpleWordCount {
  def main(args: Array[String]): Unit = {
    val stringList = List("Hello Scala Hbase kafka", "Hello Scala Hbase", "Hello Scala", "Hello")

    //    val list: List[List[String]] = stringList.map(a => a.split(" ").toList)
    val list: List[List[String]] = stringList.map(_.split(" ").toList)
    println(list)

    val flatten: List[String] = list.flatten
    println(flatten)

    val group: Map[String, List[String]] = flatten.groupBy(_ + "")
    println(group)
    /*
    mapValues在2.13中被废弃需要添加toMap
     */
    val mapLen: MapView[String, Int] = group.mapValues((list: List[String]) => list.length)
    println("mapVaule"+mapLen.toMap)
    /*
    替换为map直接统计,后面需要排序,所以需要先转换为list
     */
    //    val value: Any = group.map((tuple2:(String,List[String]))=> (tuple2._1,tuple2._2.length)).toList
    val list1: List[(String, Int)] = group.map(tuple2 => (tuple2._1, tuple2._2.length)).toList
    println("map统计"+list1)


    //排序
    //map和set无法排序,需要先转换为list
    //化简
    val value: List[(String, Int)] = stringList.map(_.split(" ").toList)
      .flatten
      .groupBy(_ + "")
      .map(tuple2 => (tuple2._1, tuple2._2.length))
      .toList
      .sortWith(_._2 > _._2)

    println(value)

    //再次化简 map 和 flatten
    val value1: List[(String, Int)] = stringList
      .flatMap(_.split(" ").toList)
      .groupBy(_ + "")
      .map(tuple2 => (tuple2._1, tuple2._2.length))
      .toList
      .sortWith(_._2 > _._2)
    println(value1)
  }
}
