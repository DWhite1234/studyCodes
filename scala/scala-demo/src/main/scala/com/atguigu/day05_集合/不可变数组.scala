package com.atguigu.day05_集合

object 不可变数组 {
  def main(args: Array[String]): Unit = {
    /*
    定义数组的方式
    1.val ints:Int = new Array[Int](10)
    2.val ints = Array(1,2,3,4)(apply方法创建)
     */

    val ints = new Array[Int](10)
    //1.数组赋值
      //下标赋值
      ints(0) = 3
      //方法赋值
      ints.update(1, 1)

    //2.快捷打印mkString
    println(ints.mkString("ints(", ",", ")"))

    //3.遍历数组
      //1.普通遍历
      for (i <- 0 to 9) {
        print(ints(i)+"\t")
      }

      println()
      //2.增强for
      for (elem <- ints) {
        print(elem+"\t")
      }
      println()
      //迭代器
      val iterator = ints.iterator
      while(iterator.hasNext) {
        print(iterator.next()+"\t")
      }
      println()
      //foreach 参数为函数
      ints.foreach((x:Int) => print(x+"\t"))
      println()
    //3.增加元素
    //末尾增加
    val ints1 = ints :+ 1
    //头部增加
    val ints2 = 1 +: ints
    println(ints1.mkString("ints(", ",", ")"),"length:"+ints1.length)
    println(ints2.mkString("ints(", ",", ")"),"length:"+ints1.length)
  }
}
