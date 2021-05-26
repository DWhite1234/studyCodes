package com.atguigu.day06_函数

object ComplexWordCount {
  def main(args: Array[String]): Unit = {
    val tupleList = List(("Hello Scala Spark World ", 4), ("Hello Scala Spark", 3), ("Hello Scala", 2), ("Hello", 1))

    val value: List[List[(String, Int)]] = tupleList.map(Tuple2 => (Tuple2._1.split(" ").toList.map(a => (a, Tuple2._2))))
    println(value)

    val flatten: List[(String, Int)] = value.flatten
    println(flatten)

    val value1: List[(String, Int)] = tupleList.flatMap(Tuple2 => (Tuple2._1.split(" ").toList.map(a => (a, Tuple2._2))))
    println(value1)

    val value2: Map[String, List[(String, Int)]] = value1.groupBy(_._1)
    println(value2)

    val value3: Map[String, Int] = value2.map(Tuple2 => {
      //通用方法
      /*
      var sum = 0
      Tuple2._2.foreach((res: (String, Int)) => sum += res._2)
      (Tuple2._1, sum)
       */
      //此处直接规约即可
      (Tuple2._1,Tuple2._2.foldLeft(0)(_+_._2))
    })
    println(value3)

    val value4: List[(String, Int)] = value3.toList.sortWith((left, right) => left._2 > right._2)
    println(value4)

    val value5: List[(String, Int)] = value4.take(3)
    println(value5)
  }
}
