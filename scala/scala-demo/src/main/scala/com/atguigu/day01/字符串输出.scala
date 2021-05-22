package com.atguigu.day01

object 字符串输出 {
  def main(args: Array[String]): Unit = {
    var name: String ="zhangsan"
    var age: Int = 20

    //1.字符串拼接输出
    println("name:"+name+"age:"+age)

    //2.格式化输出
    printf("name:%s,age:%d",name,age)
    println()
    //3.$引用输出,格式为print(s""),并且可以进行运算
    println(s"name:${name},age:${age*2}")

    //4.长连接""""""配合$
    println(
      s"""
         |我的名字是${name}
         |我的年纪是${age}
         |我爷爷的年纪是${age*6}
         |""".stripMargin)
  }
}
