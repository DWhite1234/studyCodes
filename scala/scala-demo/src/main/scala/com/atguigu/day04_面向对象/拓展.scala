package com.atguigu.day04_面向对象

object 拓展 {
  def main(args: Array[String]): Unit = {
    /*
      1.判断类型
       A.isInstanceOf[B] A是否是B类型,多用在继承关系中
      2.强制转换类型
       A.asInstanceOf[B] A强制转换成B类型

      3.获取对象的类名
        var pClass:Class[Person] = classOf[Person]

     */
    println(Color.Red)

    /*
    type定义新类型,本质是为类型起别名
     */
    type  s = String
    val name:s ="张三"
  }
}

/*
枚举类
 */
object Color extends Enumeration{
  val Red = Value(1, "red")
  val Blue = Value(2,"blue")
}

/*
应用类,可以直接执行
 */

object APP1 extends App{

}
