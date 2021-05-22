package com.atguigu.day03_函数式编程.study

object 值调用和名调用 {
  def main(args: Array[String]): Unit = {
    //    值调用
    def test1(a: Int) = {
      a
    }
    //把值传递给函数
    println(test1(10))

    //    名调用
    def test2(a: => Int):Int = {
      println("我是明天")
      a
    }
    var a:Int=0
    println(test2{
      println("开始名调用")
      a+10
    })
  }
}
