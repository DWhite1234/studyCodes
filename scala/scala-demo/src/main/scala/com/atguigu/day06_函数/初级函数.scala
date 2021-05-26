package com.atguigu.day06_函数

object 初级函数 {
  def main(args: Array[String]): Unit = {
    val list: List[Int] = List(1, 5, -3, 4, 2, -7, 6)
    //    （1）求和
    println(list.sum)
    //    （2）求乘积
    println(list.product)
    //    （3）最大值
    println(list.max)
    //    （4）最小值
    println(list.min)
    //    （5）排序
    println(list.sorted)

    println(list.sorted(Ordering.Int.reverse))
    //指定排序规则
    println(list.sortWith((left: Int, right: Int) => left > right))

    //指定按照什么字段排
    val tp = List(("hello", 1), ("hi", 2), ("ha", 3))
    println(tp.sortBy((tuple: (String, Int)) => tuple._1)(Ordering.String))
  }
}
