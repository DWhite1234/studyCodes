package com.atguigu.day01

object 变量和常量 {
  def main(args: Array[String]): Unit = {
    //声明常量
    val a: Int =10
    //变量不可以多次赋值
//    a=11 报错
    //声明变量
    var b: Int =20
    //变量可以多次赋值
    b=22


    println(b)
  }
}

