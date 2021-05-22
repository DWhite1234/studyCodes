package com.atguigu.day03_函数式编程.study

object 匿名函数 {
  def main(args: Array[String]): Unit = {
    //匿名函数:没有名字的函数,类似js 箭头函数
    val stringToString: String => String = name=> name

    println(stringToString("我是匿名函数"))

    //     (1）参数的类型可以省略，会根据形参进行自动的推导,自动推导,必须至少有一处声明了类型才能推导
    val stringToString2: String => String = name=> name
    //    （2）类型省略之后，发现只有一个参数，则圆括号可以省略；其他情况：没有参数和参数超过1的永远不能省略圆括号。
    //    （3）匿名函数如果只有一行，则大括号也可以省略
    //    （4）如果参数只出现一次，则参数省略且后面参数可以用_代替,只能用在最外层,不能嵌套使用_(_)
    val stringToString3: String => String = name=> name+""
    val stringToString4: String => String = _ +""
  }
}
