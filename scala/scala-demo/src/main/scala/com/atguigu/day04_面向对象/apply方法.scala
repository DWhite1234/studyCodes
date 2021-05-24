package com.atguigu.day04_面向对象

object apply方法 {
  def main(args: Array[String]): Unit = {
    /*
    通过apply方法可以不new 而创建对象,类似Array(1,3,2)
     */
    val cat = Cat()
    println(cat)
  }
}

class Cat{

}
object Cat {

  def getCat(): Cat = {
    new Cat
  }

  def apply(): Cat = {
    new Cat
  }
}

