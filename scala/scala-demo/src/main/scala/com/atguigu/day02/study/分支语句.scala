package com.atguigu.day02.study

object 分支语句 {
  def main(args: Array[String]): Unit = {
    //    if else 是有返回值的,if是没有返回值的
    val bool: Boolean = if (1 > 2) {
      false
    } else {
      true
    }

    println(bool)

//    所以三元运算符可以用if else 代替
    val str = if (19 > 18) "成年" else "未成年"
    println(str)
  }
}
