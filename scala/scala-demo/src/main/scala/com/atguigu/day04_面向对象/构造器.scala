package com.atguigu.day04_面向对象

object 构造器 {
  def main(args: Array[String]): Unit = {
    //无参构造创建对象,默认带一个无参构造
    val person = new Person
    //有参主构造器创建对象
    println("================有参主构造器创建对象================")
    val person1 = new Person1("张三")
    println(person1.name1)
    val person3 = new Person3("李四")
    println(person3.name)
    //无参主构造器+辅助构造器
    println("================无参主构造器+辅助构造器================")
    val person2 = new Person2
    val person2_1 = new Person2("王五")
    println(person2_1.age)
    println(person2_1.name)

    val person2_2 = new Person2("赵六", 18)
    println(person2_2.age)
    println(person2_2.name)

    println("================有参主构造器+辅助构造器================")
    val person4 = new Person4()
    println(person4.age)
    println(person4.name)
  }
}
/*
  scala的构造器分为 主构造器和辅助构造器
    主构造器:class Person{},唯一
    辅助构造器:在主构造器内定义的构造器,可以有多个,函数名称为this,可以重载,
        必须直接或间接调用主构造器,辅助构造器之间调用,必须先声明才能调用(有顺序要求)
*/


//无参主构造器
class Person {
}

//有参主构造器1
class Person1(name: String) {
  val name1 = name
}

//有参主构造器2
class Person3(val name: String) {
}


//辅助构造器+无参主构造器
class Person2 {
  var name:String = _
  var age:Int = _
  def this(age: Int) {
    this()
    this.age=age
  }

  def this(name: String) = {
    this()
    this.name=name
  }

  def this(name:String,age:Int){
    this(age)
    this.name=name
  }
}

//有参构造器+辅助构造器
class Person4(val name:String,val age:Int){
  def this(){
    this("",0)
  }
}


