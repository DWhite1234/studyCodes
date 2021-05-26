package com.atguigu.day05_集合

import scala.collection.mutable.Set
import scala.collection.mutable.HashSet

object 可变set {
  def main(args: Array[String]): Unit = {
    val hashset1 = Set(1, 2, 3, 4, 5)
    //创建
//    val hashset = new HashSet[Int]()
//    val hashset1 = HashSet(1, 2, 3, 4, 5)

    //添加数据
    hashset1.add(1000)
    println(hashset1)
    val hashset3 = hashset1 + 200
    println(hashset3)

    //删除数据
    hashset1.remove(1000)
    println(hashset1)
    val hashset4 = hashset3 - 200
    println(hashset4)


    //遍历
    hashset1.foreach(println)

    //大小
    println(hashset1.size)
  }
}
