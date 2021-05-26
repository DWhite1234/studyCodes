package com.atguigu.day05_集合

import scala.collection.mutable.ArrayBuffer

object 可变数组 {
  def main(args: Array[String]): Unit = {
    //创建可变数组,
    val arrayBuffer1 = new ArrayBuffer[Int](3)
    val arrayBuffer = ArrayBuffer[Int](1, 2, 3)

    //添加元素
    //尾部追加
    arrayBuffer += 4
    arrayBuffer.append(5)
    arrayBuffer.addOne(1)
    //头部添加
    arrayBuffer.prepend(3)
    6 +=: arrayBuffer

    //删除元素
    //删除指定下标的元素
    println("remove"+arrayBuffer)
    arrayBuffer.remove(5)
    println("remove"+arrayBuffer)
    //删除指定元素
    arrayBuffer -= 1
    println("remove"+arrayBuffer)
    //从0开始删除3个
    arrayBuffer.remove(0, 3)

    //插入数据
    arrayBuffer.insert(3, 1)

    //修改数据
    arrayBuffer.update(3, 2)
    arrayBuffer(3) = 3
    //遍历
    arrayBuffer.foreach(println)

    //合并数组
    arrayBuffer.appendAll(arrayBuffer)
    arrayBuffer ++= arrayBuffer

    //快捷打印
    println(arrayBuffer.mkString("arrayBuffer:(", ",", ")"))


    //获取指定数据
    //获取第一个数据
    println(arrayBuffer.head)
    //获取除第一个之外的数据
    println(arrayBuffer.tail)
    //获取最后一个数据
    println(arrayBuffer.last)
    //获取除最后一位的所有数据


    //可变数组与不可变数组之间的相互转换,原数组本身不收影响,返回新的数组
    val ints = Array(1, 2, 3, 4, 5)
    val ints2 = ArrayBuffer(2, 3, 4, 5, 6)
    //不可变数组转为可变数组
    val buffer = ints.toBuffer
    //可变数组转为不可变数组
    val array = ints2.toArray


    //可变多维数组
    val array1 = Array.ofDim[Int](3, 4)
    array1(0)=Array(1,2,3,4)
    array1(1)=Array(2,3,4,5)

    for (elem <- array1) {
      for (int <- elem) {
        print(int)
      }
      println()
    }

  }
}
