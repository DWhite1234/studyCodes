package com.atguigu.day03_函数式编程.study

object 参数 {
  def main(args: Array[String]): Unit = {
    //1.可变参数用数据类型+*,必须放在最后
    def testFun(name:String,s:String*): Unit = {
    }

    //2.形参可以设置默认值,但是一旦调用的时候进行了赋值,就会覆盖默认值
    def test(a: Int = 30): Unit = {
      println(a)
    }

    test(10)

    //3.带名参数,调用时可以不根据形参的顺序,指定赋值的形参名
    def test2(a:Int,b:String): Unit = {
      println(a)
      println(b)
    }

    test2(b = "sss",a = 20)


    //4.形参性质和val 声明的常量一样,不允许重新赋值
    def test3(a:Int): Unit = {
//      a = 30 编译报错
    }
  }
}
