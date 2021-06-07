package com.atguigu.realExamples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Example01_rdd {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)


    //本项目需求优化为：先按照点击数排名，靠前的就排名高；如果点击数相同，再比较下单数；下单数再相同，就比较支付数。
        val dataSource: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\user_visit_action.txt")
//    val dataSource: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\test")

    //1.计算点击数
    val value: RDD[String] = dataSource.filter(
      i => {
        val strings: Array[String] = i.split("_")
        strings(6).toInt != -1
      }
    )
    val value1: RDD[(String, Int)] = value.map(i => {
      val sList: Array[String] = i.split("_")
      (sList(6), 1)
    })
    val value6: RDD[(String, Int)] = value1.reduceByKey(_ + _)

    //计算下单数
    val value3: RDD[String] = dataSource.filter(
      i => {
        val sList: Array[String] = i.split("_")
        sList(8) != "null"
      }
    )
    val value2: RDD[(String, Int)] = value3.flatMap(
      i => {
        val sList: Array[String] = i.split("_")
        val array: Array[String] = sList(8).split(",")
        val list: List[(String, Int)] = array.map(
          i => {
            (i, 1)
          }
        ).toList
        list
      }
    )

    val value7: RDD[(String, Int)] = value2.reduceByKey(_ + _)

    //计算支付数
    val value5: RDD[String] = dataSource.filter(
      i => {
        val sList: Array[String] = i.split("_")
        sList(10) != "null"
      }
    )

    val value4: RDD[(String, Int)] = value5.flatMap(
      i => {
        val sList: Array[String] = i.split("_")
        val array: Array[String] = sList(10).split(",")
        val list: List[(String, Int)] = array.map(
          i => {
            (i, 1)
          }
        ).toList
        list
      }
    )

    val value8: RDD[(String, Int)] = value4.reduceByKey(_ + _)

    //  678
    val value9: RDD[(String, (Iterable[Int], Iterable[Int], Iterable[Int]))] = value6.cogroup(value7, value8)
    val value11: RDD[(String, (Int, Int, Int))] = value9.map(
      i => {
        val value10: (Iterable[Int], Iterable[Int], Iterable[Int]) = i._2
        var a = 0
        var b = 0
        var c = 0
        if (value10._1.toList.length > 0) {
          a = value10._1.toList(0)
        }
        if (value10._2.toList.length > 0) {
          b = value10._2.toList(0)
        }
        if (value10._3.toList.length > 0) {
          c = value10._3.toList(0)
        }
        (i._1, (a, b, c))
      }
    )

    value11.sortBy(_._2,false).take(10).foreach(println)

    Thread.sleep(Long.MaxValue)
    sc.stop()
  }
}
