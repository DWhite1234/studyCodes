package com.atguigu.day04_面向对象

object 单例模式 {
  def main(args: Array[String]): Unit = {
    val person = Person7.getPerson()
    val person1 = Person7.getPerson()
    println(person.eq(person1))
  }
}

//构造私有化
class Person7 private(){

}

object Person7{
  private val person:Person7 = new Person7

  def getPerson() = {
    person
  }
}
