package com.atguigu.day03_函数式编程.study

object 惰性函数 {
  def main(args: Array[String]): Unit = {
    //被lazy修饰的函数返回值时,函数的执行将被推迟
    def test(a:Int,b:Int) = {
      a+b
    }
    //被lazy修饰的函数结果,此时函数未执行,只有当i被调用的时候,才会开始执行对应的函数
    lazy val i = test(10, 20)
    //未被lazy修饰的i2,是函数执行完成后把值赋给i2,此时i2已经是由结果了
    val i2 = test(10, 20)
    println(i)
  }
}
