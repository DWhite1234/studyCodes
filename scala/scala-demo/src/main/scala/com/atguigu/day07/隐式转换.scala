package com.atguigu.day07

object 隐式转换 {

  //implicit 申明隐式函数
  implicit def convert(arg: Int): MyNewInt = {
    new MyNewInt
  }

  def main(args: Array[String]): Unit = {
    println(3.maxInt())
  }
}

class MyNewInt{
  def maxInt()={
    Long.MaxValue
  }
}

object MyNewInt{

}


