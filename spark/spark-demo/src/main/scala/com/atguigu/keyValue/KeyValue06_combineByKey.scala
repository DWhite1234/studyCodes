package com.atguigu.keyValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object KeyValue06_combineByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val list: List[(String, Int)] = List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))
    val rdd: RDD[(String, Int)] = sc.makeRDD(list, 2)

    /*
    createCombiner: V => C,
      每个分区内的每种key执行一次,将第一次的数据转换数据结构(自己按照需要指定数据结构)
    mergeValue: (C, V) => C,
      在分区内,将转换过的数据结构与未转换过的数据结构进行合并(按照需求合并)
    mergeCombiners: (C, C) => C): RDD[(K, C)]
      分区间相同的key聚合
     */
    //需求 创建一个pairRDD，根据key计算每种key的平均值。（先计算每个key出现的次数以及可以对应值的总和，再相除得到结果）
    rdd.combineByKey(i=>(i,1),
      (t:(Int,Int),b:Int)=>(t._1+b,t._2+1),
      (t1:(Int,Int),t2:(Int,Int))=>(t1._1+t2._1,t1._2+t2._2)
    ).collect().foreach(println)


    /*
    reduceByKey、foldByKey、aggregateByKey、combineByKey

    相同:底层都是调用的同一个方法:combineByKeyWithClassTag
    区别:
      reduceByKey:没有初始值,分区间和区内逻辑一直,有预聚合
      aggregateByKey:有初始值,分区内和分区间逻辑不一致
      foldByKey:有初始值,分区内和分区间逻辑一致
      combineByKey:有初始值,并且初始值还可以更改数据结构,更加灵活
     */

    //wordcount
    val value: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\1.txt")
    value.flatMap(i => i.split(" ")).map(i =>(i,1)).combineByKey(i=>i,(a:Int,b:Int)=>a+b,(a:Int,b:Int)=>a+b).collect().foreach(println)
    Thread.sleep(Long.MaxValue)
    sc.stop()
  }
}
