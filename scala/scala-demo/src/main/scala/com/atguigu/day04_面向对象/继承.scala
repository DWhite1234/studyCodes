package com.atguigu.day04_面向对象

object 继承 {
  def main(args: Array[String]): Unit = {
//    val student = new Student(29)

    val student1 = new Student2()
  }
}

class Person5{
  var name:String = _
  var age:Int = _
  println("父类主构造器")
  def this(age: Int) {
    this()
    this.age=age
    println("父类辅助构造器")
  }

  def this(name:String,age:Int){
    this(age)
    this.name=name
  }
}

class Student extends Person5 {
  //override var name:String = _
  var stuName:String = _
  println("子类主构造器")
  def this(name:String) = {
    this()
    this.stuName=name
    println("子类辅助构造器")
  }

  def this(age:Int) {
    this("李四")
    println("子类辅助构造器")
  }
}

//extends Person5(stuName) 指定了调用父类的有参还是无参构造器
class Student2(var stuName:Int) extends Person5(stuName) {
  //override var name:String = _
  println("子类主构造器")
  def this() = {
    this(0)
    println("子类辅助构造器")
  }
}