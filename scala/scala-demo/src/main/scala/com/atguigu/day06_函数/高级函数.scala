package com.atguigu.day06_函数

object 高级函数 {
  def main(args: Array[String]): Unit = {
    var list = List(1, 3, 2, 4, 5, 6, 7, 8, 9)
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
    /*
      归约:类似求和sum
      reduce op:(B,B)=>B 参数必须为两个
      第一个B:初始值(用集合中的元素作为初始值)
      第二个B:用来计算的值
      第三个B:结果

      用初始值每次和新的计算值计算
      reduce,reduceLeft 一样,二者选其一,reduceRight逻辑比较奇特,
      注意点:reduce和reduceLeft默认选取第一个作为初始值,如果用作负数计算,那么这个初始值会不符合计算规则
     */
    val listReduce: List[(String, Int)] = List(("我是", 1), ("我是", 2), ("我是", 3), ("我是", 4))
    val tuple: (String, Int) = listReduce.reduce((res: (String, Int), element: (String, Int)) => (res._1, res._2 + element._2))
    println(tuple)

    val tuple1: (String, Int) = listReduce.reduceLeft((res: (String, Int), element: (String, Int)) => (res._1, res._2 + element._2))
    println(tuple1)

    //    （7）折叠
    /*
    fold 有初始值(自定义初始值)
    foldLeft((初始值))((res:初始值类型),ele:元素类型)=>())
     */
    val listFold: List[(String, Int)] = List(("我是", 1), ("我是", 2), ("我是", 3), ("我是", 4))
    val tuple2: (String, Int) = listFold.foldLeft(("我是", 0))((res: (String, Int), ele: (String, Int)) => (res._1, +res._2 + ele._2))
    println(tuple2)
    val tuple3: (String, Int) = listFold.fold(("我是", 0))((res: (String, Int), ele: (String, Int)) => (res._1, res._2 + ele._2))
    println(tuple3)
  }
}
