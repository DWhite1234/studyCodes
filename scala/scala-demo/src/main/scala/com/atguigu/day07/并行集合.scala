package com.atguigu.day07
import scala.collection.parallel.CollectionConverters._
object 并行集合 {
  def main(args: Array[String]): Unit = {
    (0 to 100).foreach(i => println(Thread.currentThread().getName + i))

    //2.13中par成了外部库,需要单独引进
    /*
      <dependency>
            <groupId>org.scala-lang.modules</groupId>
            <artifactId>scala-parallel-collections_2.13</artifactId>
            <version>0.2.0</version>
        </dependency>

    import scala.collection.parallel.CollectionConverters._

     */
    ((0 to 100).par.foreach(i => println(Thread.currentThread().getName + i)))
  }
}
