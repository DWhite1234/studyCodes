package com.atguigu.day05_集合

import scala.collection.mutable.Map

object 可变的map {
  def main(args: Array[String]): Unit = {
    val map:Map[String,Int] = Map()

    //添加数据
    map.put("hello",1)

    //删除数据
    //被Option包装过的类型,可以用来判断,如果有值则是Some,some.get 如果没值则是None,None.isEmpty
    val maybeInt:Option[Int] = map.remove("hihi")
    println(maybeInt)

    //修改值
    map.update("hello",2)
    map("hello")=3

    //打印map
    map.foreach(println)

    for ( ele <- map ) {
      println(ele._1+":"+ele._2)
    }

    //判断是否包含指定key
    map.contains("hello")
  }
}
