package com.atguigu.day04_面向对象

import scala.beans.BeanProperty

object 类和对象 {
  def main(args: Array[String]): Unit = {
    val obj = new classAndObj
    println(obj.a)
    obj.b = "set"
    println(obj.b)
    obj.setD("set方法")
    println(obj.getD)
  }
}
//scala中的类默认是 public,一个scala原文件可以包含多个类
class classAndObj{
  //定义属性
  val a:Int = 10
  var b:String = "str"
  //用 _ 设置默认值,val不能设置默认值,必须显示赋值
  var c:String = _

  //通过@BeanProperty 显示的设置set get方法, val只有get方法,var 有get和set方法
  @BeanProperty
  var d:String = "设置set和get"
}
//
//class 类和对象1{
//
//}
//
//class 类和对象2{
//
//}


