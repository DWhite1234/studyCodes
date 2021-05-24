package com.atguigu.day04_面向对象

object 抽象 {

}


abstract class Animal {
  //非抽象的属性
  var animalNmae: String = "动物"
  val age: Int = 3

  //抽象属性
  val name: String

  //抽象方法
  def sayHi()

  def sayHi2(): Unit = {
    println("我是动物")
  }
}

class Dog extends Animal {
  /*
  抽象的方法和属性必须重写,其他的想重写就重写
  */
  //抽象属性或方法重写不必须加override,
  val name: String = "dog"
  //非抽象方法重写必须加override
  override val age: Int = 1

  override def sayHi() {
    println("子类重写抽象方法和属性")
  }
}