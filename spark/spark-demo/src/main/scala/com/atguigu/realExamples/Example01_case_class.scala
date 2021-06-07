package com.atguigu.realExamples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Example01_case_class {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //先求Top10
    val dataSource: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\user_visit_action.txt")
    //    val dataSource: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\test")
    val userActionRdd: RDD[UserAction] = dataSource.map(
      i => {
        val list: Array[String] = i.split("_")
        //利用apply创建样例类
        UserAction(
          list(0),
          list(1),
          list(2),
          list(3),
          list(4),
          list(5),
          list(6),
          list(7),
          list(8),
          list(9),
          list(10),
          list(11),
          list(12)
        )
      }
    )
    //为输出结构赋初值
    val value: RDD[ExampleTop10] = userActionRdd.flatMap(
      user => {
        if (user.click_category_id != "-1") {
          List(ExampleTop10(user.click_category_id, 1, 0, 0))
        } else if (user.order_category_ids != "null") {
          val str1: Array[String] = user.order_category_ids.split(",")
          str1.map(i => {
            ExampleTop10(i, 0, 1, 0)
          })
        } else if (user.pay_category_ids != "null") {
          val str2: Array[String] = user.pay_category_ids.split(",")
          str2.map(
            i => {
              ExampleTop10(i, 0, 0, 1)
            }
          )
        } else {
          Nil
        }
      }
    )
    //产品 id分区
    val productIdRdd: RDD[(String, Iterable[ExampleTop10])] = value.groupBy(user => {
      user.product_id
    })

    //分组计算各个参数的值
    val value1: RDD[(String, ExampleTop10)] = productIdRdd.mapValues(
      list => {
        list.reduce(
          (info1, info2) => {
            info1.clickCount += info2.clickCount
            info1.orderCount += info2.orderCount
            info1.payCount += info2.payCount
            info1
          }
        )
      }
    )
    //转换数据结构 (key,ExampleTop10)==>ExampleTop10
    val value2: RDD[ExampleTop10] = value1.map(
      i => i._2
    )

    //排序取前10
    val top10: Array[ExampleTop10] = value2.sortBy(i => (i.clickCount, i.orderCount, i.payCount), false).take(10)
    top10.foreach(println)


    sc.stop()
  }
}
