package com.atguigu.day03_函数式编程.study

object 函数化简 {
  def main(args: Array[String]): Unit = {

    def test(b: String): String = {
      b + "string"
    }
    //    （1）return可以省略，Scala会使用函数体的最后一行代码作为返回值
    println(test("hello")) // hellostring

    //    （2）如果函数体只有一行代码，可以省略花括号
    def test1(b: String): String = b + "string"

    //    （3）返回值类型如果能够推断出来，那么可以省略（:和返回值类型一起省略）
    def test2(b: String) = b + "string"

    //    （4）如果有return，则不能省略返回值类型，必须指定
    def test3(b: String): String = return b + "string"

    //    （5）如果函数明确声明unit，那么即使函数体中使用return关键字也不起作用
    def test4(b: String): Unit = return b + "string"

    print(test4("hi")) //()

    //    （6）Scala如果期望是无返回值类型，可以省略等号
    def test5(b: String) {
      b + "string"
    }

    print(test5("hi")) //()

    //    （7）如果函数无参，但是声明了参数列表，那么调用时，小括号，可加可不加
    def test6() {
      "string"
    }

    test6()
    test6

    //    （8）如果函数没有参数列表，那么小括号可以省略，调用时小括号必须省略
    def test7 {
      "string"
    }

    test7
    //    （9）如果不关心名称，只关心逻辑处理，那么函数名（def）可以省略
    val str: () => String = () => {
      "hello"
    }

    println("9"+str())


  }
}
