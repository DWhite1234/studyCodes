package com.atguigu.value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object value09_sample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(1 to 8)
    /*
    def sample(
            withReplacement: Boolean,
            fraction: Double,
            seed: Long = Utils.random.nextLong): RDD[T]
     withReplacement： true为有放回的抽样，false为无放回的抽样；
     fraction表示：以指定的随机种子随机抽样出数量为fraction的数据；
     seed:指定种子数,种子数相同的数据必定相同
     */
    /*
     抽取数据不放回（伯努利算法）
     伯努利算法：又叫0、1分布。例如扔硬币，要么正面，要么反面。
     具体实现：根据种子和随机算法算出一个数和第二个参数设置几率比较，小于第二个参数要，大于不要
     第一个参数：抽取的数据是否放回，false：不放回
     第二个参数：抽取的几率，范围在[0,1]之间,0：全不取；1：全取；
     第三个参数：随机数种子
     */
    val value: RDD[Int] = rdd.sample(false, 0.5)
    value.collect().foreach(println)

    println("===========================")
    /*
     抽取数据放回（泊松算法）
     第一个参数：抽取的数据是否放回，true：放回；false：不放回
     第二个参数：重复数据的几率，范围大于等于0.表示每一个元素被期望抽取到的次数
     第三个参数：随机数种子
     */
    val value1: RDD[Int] = rdd.sample(true, 1)
    value1.collect().foreach(println)
    Thread.sleep(Long.MaxValue)
    sc.stop()
  }
}
