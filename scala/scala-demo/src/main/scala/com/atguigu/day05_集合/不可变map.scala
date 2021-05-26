package com.atguigu.day05_集合

import scala.collection.mutable.HashMap

object 不可变map {
  def main(args: Array[String]): Unit = {
    val map1 = Map("haha" -> 1, "hihi" -> 2)
    val map2 = Map(("hello", 1), ("hihi", 2))

    //遍历
    map1.foreach(println)

    //获取所有的key
    val keys = map1.keys
    keys.foreach(println)

    //获取所有的value
    val values = map1.values
    values.foreach(println)


    //访问数据
    //1.option
    val option = map1.get("haha")
    //如果没有对应的key则异常None.get
    println("option:\t"+option.get)

    //2.指定默认值
    val i = map1.getOrElse("hihi", 1)
    println("getOrElse:\t"+i)

    //3.map的apply方法
    //如果没有对应的key则异常key not found
    println(map1("hihi"))
  }
}
