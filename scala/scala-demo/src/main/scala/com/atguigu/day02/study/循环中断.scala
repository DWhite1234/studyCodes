package com.atguigu.day02.study

import scala.util.control.Breaks

object 循环中断 {
  def main(args: Array[String]): Unit = {
    println("=============================异常中断=========================")
    try {
      for (i <- 1 to 10) {
        println(s"i = ${i}")
        if (i > 5) {
          throw new RuntimeException()
        }
      }
    } catch {
      case e => println("异常结束")
    }
    println("循环结束")
    
    println("================================Breaks.breakable================")
    Breaks.breakable(
      for (i <- 1 to 10) {
        println(s"i = ${i}")
        if (i>4){
          Breaks.break()
        }
      }
    )
    println("循环结束")
  }
}
