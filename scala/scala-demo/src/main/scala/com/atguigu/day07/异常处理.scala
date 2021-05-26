package com.atguigu.day07

object 异常处理 {
  def main(args: Array[String]): Unit = {
    try {
      throw new RuntimeException
    } catch {
      //模式匹配捕捉异常
      case e: RuntimeException => println("RuntimeException")
      case e: Exception => println("Exception")
    }
  }
}
