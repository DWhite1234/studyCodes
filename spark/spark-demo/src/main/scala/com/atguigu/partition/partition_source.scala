package com.atguigu.partition

object partition_source {
  def main(args: Array[String]): Unit = {
    /*
    1.hash分区器
    对key进行hash 除于分区的个数 取余

    缺点:有可能导致几个数据量极大的分区,进入同一个分区导致数据倾斜
     */

    /*
    2.ranger分区
    将一定范围内的值,映射到同一个分区
    1.取样,采用水塘抽样算法,计算出每个分区的值的范围
    2.判断key处于对应的那个分区内,要求该key必须是可以排序的


     */
  }
}
