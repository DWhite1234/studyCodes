package com.atguigu.day04_面向对象

object 多态 {
  def main(args: Array[String]): Unit = {
    /*
     scala中没有多态,多态与直接创建子类对象是一模一样的效果
     原因:父类的属性以及方法都可以被重写
     注意点:父类的var 可变属性无法被重写,运行报错
           因为var修饰的为可变变量，子类继承之后就可以直接使用，没有必要重写
    */
    var person6:Person6 = new student3("小明")
    println(person6.Name)
    person6.sayHi()
  }
}

class Person6{
  val Name:String= ""

  def sayHi() = {
    println("父类方法")
  }
  def this(name:String) = {
    this()
  }
}

class student3 extends Person6 {
  override val Name:String = "小黑"

  override def sayHi(): Unit = {
    println("子类方法")
  }

  def this(name:String) = {
    this()
  }
}
