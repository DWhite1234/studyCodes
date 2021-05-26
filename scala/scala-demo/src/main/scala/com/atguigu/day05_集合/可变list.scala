package com.atguigu.day05_集合

import scala.collection.mutable.ListBuffer

object 可变list {
  def main(args: Array[String]): Unit = {
    //创建可变list
    val list1 = new ListBuffer[Int]()
    val list2 = ListBuffer(1, 2, 3, 4)

    //添加数据
    list1.append(1, 3, 2, 4)
    list1.addOne(5)
    list1 += 6
    list1.prepend(7)
    8 +=: list1

    //删除数据
    //删除指定下标
    list1.remove(2)
    println(list1)
    list1.remove(2,2)
    println(list1)
    //删除指定元素
    list1-=6
    println(list1)

    val list3 = list1.drop(2)
    println("删除节点,返回剩余节点"+list3)

    val list4 = list1.take(2)
    println("take 返回删除的节点"+list4)

    //修改元素
    list1.update(0,1)
    println(list1)
    list1(0)=8
    println(list1)

    //遍历
    list1.foreach(println)
  }
}
