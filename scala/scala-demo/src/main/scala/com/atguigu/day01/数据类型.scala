package com.atguigu.day01

object 数据类型 {
  def main(args: Array[String]): Unit = {
    /*
        1.scala中所有的数据类型都是any的子类
        2.any分为:anyVal(基本数据类型,对象)和antRef(引用数据类型,对象)
        3.any同样遵守自动类型转换(隐式转换)
        4.Unit对应java 的void,表示没有返回值,只有一个对象()
        5.Null是一个类型,只有一个对象null,他是所有引用类型的anyRef的子类
        6.nothing是所有数据类型的子类,主要用于(异常)没有办法正确返回时返回的值
     */

    //3.any同样遵守自动类型转换(隐式转换)
    val a: Byte = 20
    val b: Int = 30
    var c: Int =a+b

      //scala内部做了优化,如果是两个数值直接运算,会根据运算结果在推断类型
//      var e:Byte=20+20
//      println(e)
      //如果是变量和数值运算,不会先进行运算在推断,而是直接推断,整形数据默认Int
//      var f:Byte=a+20
//      println(f)


    //4.Unit对应java 的void,表示没有返回值,只有一个对象()
    def test(a:Int):Unit={

    }
      print(test(1))

    //5.Null是一个类型,只有一个对象null,他是所有引用类型的anyRef的子类
    var str:String =null
    print(str)


    //6.nothing是所有数据类型的子类,主要用于(异常)没有办法正确返回时返回的值
    def testNothing(a:Int):Any={
      if(a<0) {
        throw new RuntimeException()
      }else if (a==0) {
        return 6
      }else{
        return ""
      }
    }

    println(testNothing(0))
  }
}
