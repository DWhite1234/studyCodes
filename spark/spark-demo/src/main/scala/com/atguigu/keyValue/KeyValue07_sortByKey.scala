package com.atguigu.keyValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object KeyValue07_sortByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((3, "aa"), (6, "cc"), (2, "bb"), (1, "dd")))
    //可以指定升序(true),降序(false),默认升序
    rdd.sortByKey(false).collect().foreach(println)

    /*
    补充:如果排序的是自定义的类,则需要
    K必须实现Ordered接口，返回一个按照key进行排序的(K,V)的RDD
     */
    val value: RDD[(Person, Int)] = sc.makeRDD(Array((new Person(18), 1), (new Person(20), 2), (new Person(16), 3)))

    value.sortByKey().collect().foreach(println)
    Thread.sleep(Long.MaxValue)

    sc.stop()
  }
}
/*
自定义类
1.分别实现Ordered[Person]接口(特质) 和 序列化(1.作为样例类,必须加() 2.实现Serializable)
2.重写compare方法
 */
case class Person() extends Ordered[Person]{
  var age:Int = _
  def this(age:Int) = {
    this()
    this.age=age
  }

  override def compare(that: Person): Int = {
    if (this.age>that.age) {
      -1
    }else
      1
  }

  override def toString: String = s"${age}"
}
