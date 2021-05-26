package com.atguigu.day05_集合

object 元祖 {
  def main(args: Array[String]): Unit = {
    //创建二元组,最对22个
    val tuple = new Tuple2[String, Int]("hello", 1)
    //简单方式创建
    val tuple1 = ("hihi", 2)

    //访问元祖的方式
    //从1开始 一一对应
    println(tuple._1)
    println(tuple._2)

    //从0开始 一一对应
    println(tuple.productElement(0))
    println(tuple.productElement(1))

    //遍历
    val iterator = tuple.productIterator

    iterator.foreach(println)

    while(iterator.hasNext) {
      println("迭代器遍历"+iterator.next())
    }

    for (ele <- iterator ) {
      println("增强for遍历"+ele)
    }

    //嵌套元祖
    val tuple2 = (("hello", 1), ("hi", 2), ("ha", 3))
    val productIterator = tuple2.productIterator
    productIterator.foreach(println)
  }
}
