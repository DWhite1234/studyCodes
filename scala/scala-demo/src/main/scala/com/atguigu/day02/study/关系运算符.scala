package com.atguigu.day02.study

object 关系运算符 {
  def main(args: Array[String]): Unit = {
//    注意点:scala的==和java 的==有区别
//    scala的== 比较的是内容,如果需要比较地址则是 eq()
    val str1 = new String("hello")
    val str2 = new String("hello")
    val str3 = "hello"

    //比较内容
    println(str1==str2)
    println(str1==str3)
    //比较地址
    println(str2.eq(str1))
  }
}
