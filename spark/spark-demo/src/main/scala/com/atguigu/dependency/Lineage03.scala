package com.atguigu.dependency

object Lineage03 {
  def main(args: Array[String]): Unit = {
    /*
    窄依赖:不走shuffle的依赖
    宽依赖:走shuffle的依赖


    注意:在不影响业务要求的情况下，要尽量避免使用有宽依赖的转换算子，因为有宽依赖,就一定会走shuffle，影响性能
     */
  }
}
