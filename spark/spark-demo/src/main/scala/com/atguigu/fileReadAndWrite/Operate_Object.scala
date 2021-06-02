package com.atguigu.fileReadAndWrite

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Operate_Object {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //写出对象
    val value: RDD[MyPerson] = sc.makeRDD(List(new MyPerson),1)
    value.saveAsObjectFile("ObJ")

    //读取对象文件,同样需要指定类型
    val value1: RDD[MyPerson] = sc.objectFile[MyPerson]("ObJ")
    value1.collect().foreach(i=> println(i.toString))

    sc.stop()
  }
}

class MyPerson extends Serializable {
  val name:String="zs"

  override def toString: String = s"namne:${name}"
}
