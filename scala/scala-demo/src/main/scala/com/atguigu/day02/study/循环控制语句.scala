package com.atguigu.day02.study

import scala.language.postfixOps

object 循环控制语句 {
  def main(args: Array[String]): Unit = {
    //标准写法
    for (i <- 1 to 9 ) {
      println(s"i = ${i}")
    }
    println("=========to和until的区别===================")
    //to 包含end 值,until不包含end 值
    for (i <- 1 until 9+1) {
      println(s"i = ${i}")
    }
    println("==============循环守卫==================")
    //循环守卫,将进入循环体的条件写在循环内
    for ( i<- 1 to 10 if i < 5 ) {
      println(s"i = ${i}")
    }
    println("=================循环步长====================")
    for (i <- 1 to 10 by (2) ) {
      println(s"i = ${i}")
    }

    println("====================嵌套循环======================")
    for (i<- 1 to 4;j<- 2 to 3) {
      println(s"i = ${i} j = ${j}")
    }

   for {
     a <- 1 to 4
     b <- 2 until  3
       } {
     println(s"i = ${a} j = ${b}")
     }
    
    println("=======================引入变量====================")
    for (a <- 1 to 3;j = a +1) {
      println(s"a + j = ${a + j}")
    }

    println("======================循环返回值======================")
    val value = for (i <- 1 to 8) yield {
      "***"
      //最后一行是返回值
      s"--${i}--"
    }
    println(s"value = ${value}")

    println("====================倒序打印==========================")
    for (a <- 1 to 10 by(2) reverse ) {
      println(s"a = ${a}")
    }
  }
}
