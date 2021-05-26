package com.atguigu.day05_集合

object 衍生集合 {
  def main(args: Array[String]): Unit = {
    val list1: List[Int] = List(1, 2, 3, 4, 5, 6, 7)
    val list2: List[Int] = List(4, 5, 6, 7, 8, 9, 10)

//    （1）获取集合的头
    println(list1.head)
//    （2）获取集合的尾（不是头的就是尾）
    println(list1.tail)
//    （3）集合最后一个数据
    println(list1.last)
//    （4）集合初始数据（不包含最后一个）
    println(list1.init)
//    （5）反转
    println(list1.reverse)
//    （6）取前（后）n个元素
    println(list1.take(4))
    println(list1.takeRight(3))
//    （7）去掉前（后）n个元素
    println(list1.drop(4))
    println(list1.dropRight(3))
//    （8）并集
    println(list1.union(list2))
//    （9）交集
    println(list1.intersect(list2))
//    （10）差集
    println(list1.diff(list2))
    println(list2.diff(list1))
//    （11）拉链
    //如果两个集合元素个数不相等,不能组成拉链的会被去掉
    println(list1.zip(list2))
//    （12）滑窗
    //默认步长为1
    list1.sliding(3).foreach(print)
    println()
    list1.sliding(3,4).foreach(print)

  }
}
