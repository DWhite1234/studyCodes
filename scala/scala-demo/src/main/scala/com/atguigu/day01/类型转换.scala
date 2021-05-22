package com.atguigu.day01

object 类型转换 {
  def main(args: Array[String]): Unit = {
//    1.自动类型转换
//    （byte，short）和char之间不会相互自动转换
    val a:Byte=10
    val b:Short=20
//    val c:Char=a

//    byte，short，char他们三者可以计算，在计算时首先转换为int类型。
//    val d: Int =c+a


//    2.强制类型转换
    //需要转换成什么类型就to什么类型
    //高精度转换成低精度会有精度损失
    val str:Int="222".toInt


    //例如:130.toByte
    //130超出了Byte的最大值127
    //....0000 0000 1000 0010-->1000 0010 (-2)
    //负数以补码的形式存储,利用补码转求源码-->1111 1101 (-126)
    //负数的原码和补码的和等于-128 2^7
    val e:Byte=130.toByte
    println(e)





  }
}
