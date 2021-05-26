package com.atguigu.day05_集合

import scala.collection.immutable.HashSet

object 不可变set {
  def main(args: Array[String]): Unit = {
    //set 默认 数据小于4时,有默认的set,大于四时 默认使用hashset
    val set = Set(1, 3, 4, 5, 6)
    val set1 = Set(1, 2)
    println(set.isInstanceOf[HashSet[Int]])
    println(set1.isInstanceOf[HashSet[Int]])

    //直接使用hashset创建
    val hashSet = HashSet(1, 2, 3, 4, 5)
    val hashset11 = new HashSet[Int]()

    //添加数据
    val hashset1 = hashSet + 5
    println(hashset1)
    //删除数据
    val hashset2 = hashSet - 1
    println(hashset2)

    //遍历
    hashSet.foreach(println)
  }
}
