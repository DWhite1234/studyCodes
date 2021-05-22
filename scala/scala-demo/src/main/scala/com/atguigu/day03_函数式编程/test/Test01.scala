package com.atguigu.day03_函数式编程.test

object Test01 {
  def main(args: Array[String]): Unit = {
    def isInstance(a:Int,b:Char,c:String):Boolean= {
      if (a==0 && b=='0'&& c=="") {
        false
      }else{
        true
      }
    }

    println(isInstance(0, '0', "2"))

    def isBoolean(a:Int,b:Char,c:String): Boolean = {
      def isChar(e:Char): Boolean = {
        def isString(f: String): Boolean = {
          f == ""
        }
        isString(c)
      }
      isChar(b)
    }

    println(isBoolean(0, '0', "1"))


  }
}
