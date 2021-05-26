package com.atguigu.day05_集合

object 不可变list {
  def main(args: Array[String]): Unit = {
//    （1）List默认为不可变集合
//    （2）创建一个List（数据有顺序，可重复）
    val list = List(1, 2, 3, 4, 5)
//    （3）遍历List
    list.foreach(println)
//    （4）List增加数据
    val list1 = list :+ 1
    println(list1)
    val list2 = 1 +: list
    println(list2)
    val list6 = 2 :: list
    println(list6)
//    （5）集合间合并：将一个整体拆成一个一个的个体，称为扁平化
    val list3 = list1 :+ list2
    println(list3)
    val list4 = list1 :: list2
    println(list4)
    val list5 = list1 ::: list2
    println(list5)
//    （6）取指定数据
    println(list(0))

//    （7）空集合Nil
    val list7 = 1 :: 2 :: 3 :: 4 :: Nil
    println(list7)

  }
}
