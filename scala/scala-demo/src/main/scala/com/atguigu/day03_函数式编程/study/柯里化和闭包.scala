package com.atguigu.day03_函数式编程.study

import scala.annotation.tailrec

object 柯里化和闭包 {
  def main(args: Array[String]): Unit = {
    //    闭包：如果一个函数，访问到了它的外部（局部）变量的值，那么这个函数和他所处的环境，称为闭包
    //从递归的角度说明 闭包的区别
    //1.单纯的递归.算阶乘

    def test1(a: Long): Long = {
      if (a == 1) {
        1
      } else {
        test1(a - 1) * a
      }
    }

    val s1 = System.currentTimeMillis()
    println(test1(30))
    println(System.currentTimeMillis() - s1)

    //2.闭包递归,尾递归
    def test2(a: Long) = {
      @tailrec
      def test3(a: Long, res: Long): Long = {
        if (a == 1) {
          res
        } else {
          test3(a - 1, res * a)
        }
      }

      test3(a, 1)
    }

    val s2 = System.currentTimeMillis()
    println(test2(30))
    println(System.currentTimeMillis() - s2)

    /*
    总结:
      不使用闭包的递归:只是无限次的重复调用自身,函数本身无法得到释放
      使用闭包的递归:单独将内层函数使用到的变量封装留存,但是函数本身却会被释放掉
    */

    //    函数柯里化：把一个参数列表的多个参数，变成多个参数列表,柯里化必然包含闭包
    //方式一
    def test3(a: Int, b: Int, c: Int): Int = {
      a + b + c
    }

    println(test3(10, 20, 30))
    //方式二
    def test4(a: Int) = {
      def test5(b: Int) = {
        def test6(c: Int) = a + b + c
        test6 _
      }
      test5 _
    }
    //上一步简化
    def test7(a: Int) =
      (b: Int) => (c: Int) => a + b + c

    //柯里化方式
    def test8(a:Int)(b:Int)(c:Int) ={
      a + b + c
    }
    println(test4(10)(20)(30))
    println(test8(10)(20)(30))
  }
}
