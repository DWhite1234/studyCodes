package com.atguigu.day06_函数

object 高级函数 {
  def main(args: Array[String]): Unit = {
    val list: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val nestedList: List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    val wordList: List[String] = List("hello world", "hello atguigu", "hello scala")
    //    （1）过滤
    //    遍历一个集合并从中获取满足指定条件的元素组成一个新的集合
    println(list.filter(i => i > 3))
    //    （2）转化/映射（map）
    //    将集合中的每一个元素映射到某一个函数
    val list2 = wordList.map(i => i.split(" ").toList)
    //    （3）扁平化
    val flatten = list2.flatten
    println(flatten)
    //    （4）扁平化+映射 注：flatMap相当于先进行map操作，在进行flatten操作
    //    集合中的每个元素的子元素映射到某个函数并返回新集合
    val flattenMap = wordList.flatMap(i => i.split(" ").toList)
    println(flattenMap)
    //    （5）分组(group)
    //    按照指定的规则对集合的元素进行分组
    val group = flattenMap.groupBy(a => a)
    println(group)
    //    （6）简化（归约）
    val len = group.map(tuple2 => (tuple2._1, tuple2._2.length))
    println(len)
    //    （7）折叠

  }
}
