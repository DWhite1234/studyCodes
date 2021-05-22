package com.atguigu.day03_函数式编程.study

object 高阶函数 {
  def main(args: Array[String]): Unit = {
    //    1）函数可以作为值进行传递
    def test1(name: String) = {
      name
    }

    //调用函数,并将返回值赋值给sss
    val sss = test1("sss")
    println(sss)
    // 把函数本身作为值赋值给fun
    val fun = test1 _
    println(fun)
    //调用fun
    val str = fun("我是值传递")
    println(str)



    //    2）函数可以作为参数进行传递

    def test2(fx: String => String) = {
      val name = "我是作为参数进行传递"
      fx(name)
    }

    println(test2(test1))
    //    3）函数可以作为函数返回值返回

    def test3() = {
      def test4(name:String) = {
        name
      }
      test4 _
    }

    val stringToString = test3()
    println(stringToString("我是作为参数返回值传递"))
  }
}
