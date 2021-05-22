package com.atguigu.day02.test

object Test {
  def main(args: Array[String]): Unit = {
//    9*9
    for {
      i <- 1 to 9
      j <- 1 to i
          }{
      print(s"$i*$j="+(i*j)+"\t")
      if (j==i) {
        println()
      }
    }

//    妖塔
    for (i <- 1 to 9) {
      for(j<-1 to (9-i)){
        print("  ")
      }
      for(k<- 1 to 2*i-1){
        print("* ")
      }
      println()
    }
  }

}
