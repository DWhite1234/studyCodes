package com.atguigu.day07

object 模式匹配 {
  def main(args: Array[String]): Unit = {
    //基本语法
    var a = 10
    var b = 20
    var c = '-'

    val i: Int = c match {
      case '-' => a - b
      case '*' => a * b
      case '+' => a + b
      case '/' => a / b
      case _ => 0
    }
    println(i)


    //模式守卫
    //case 依次执行,满足条件的输出
    def testMatch(x: Int) = x match {
      case a: Int if a > 0 => 0
      case b: Int if b < 0 => b
      case _ => "error"
    }

    println(testMatch(-1))


    //类型匹配
    def testTypeMatch(x: Any) = x match {
      //匹配常量
      case 'c' => "char 类型"
      case 1 => "整数类型"
      case "string" => "字符串类型"
      //匹配集合
      case List(0, 1, 2) => "匹配 0 ,1 ,2的list"
      case List(0, _*) => "匹配0开头的集合"
      case List(x, y) => "匹配两个元素的集合"
      //匹配数据类型
      case i: Int => "int类型"
      case i: String => "String类型"
      case i: List[_] => "List类型"
      //匹配数组
      case Array(1, _*) => "以1开头的数组"
      case Array(1, 2) => "1,2的数组"
      case Array(x, y) => "两个元素的数组"

      //匹配元祖
      case (x, y) => "匹配两个元素的元组"
      case (0, y) => "匹配0开头的的元组"
    }

    //偏函数
    val list = List(1,2,3,4,5,6,"test")

    list.map(i=>i match {
      case i:Int=> i+1
      case i:String => i
    })

    list.map {
      case i: Int => i + 1
      case i: String => i
    }

    list.collect({
      case i: Int => i + 1
      case i: String => i
    }).foreach(print)





    println(testTypeMatch((2, 1)))
  }
}
