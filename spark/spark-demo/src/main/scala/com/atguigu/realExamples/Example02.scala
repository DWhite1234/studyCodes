package com.atguigu.realExamples

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Example02 {
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

    println("==================================品类Top10结束=================================")

    val longs: Array[String] = top10.map(_.product_id)



    //取出每条数据 的品类id和sessionid,不区别点击,下单,支付
    //创建广播变量
    val acc: Broadcast[Array[String]] = sc.broadcast(longs)
    val value3: RDD[String] = dataSource.filter(
      i => {
        val strings: Array[String] = i.split("_")
        val bro: Array[String] = acc.value
        val strings1: Array[String] = strings(8).split(",")
        val strings2: Array[String] = strings(10).split(",")
        //        bro.contains(strings(6)) || bro.intersect(strings1) != Nil || bro.intersect(strings2) != Nil
        bro.contains(strings(6)) || bro.intersect(strings1).size > 0 || bro.intersect(strings2).size > 0
//        bro.contains(strings(6))
      }
    )

    val value4: RDD[(String, Int)] = value3.flatMap(
      i => {
        val strings: Array[String] = i.split("_")
        if (strings(6) != "-1") {
          List((strings(6) + "--" + strings(2), 1))
        } else if (strings(8) != "null") {
          strings(8).intersect(acc.value).split(",").map(
            a => {
              (a + "--" + strings(2), 1)
            }
          )
        } else if (strings(10) != "null") {
          strings(10).intersect(acc.value).split(",").map(
            a => {
              (a + "--" + strings(2), 1)
            }
          )
        } else {
          Nil
        }
      }
    )

    val value5: RDD[(String, Iterable[Int])] = value4.groupByKey()
    val value6: RDD[(String, Int)] = value5.mapValues(
      i => i.sum
    )

    val value7: RDD[(String, (String, Int))] = value6.map(
      i => {
        val strings: Array[String] = i._1.split("--")
        (strings(0), (strings(1), i._2))
      }
    )

    val value8: RDD[(String, Iterable[(String, Int)])] = value7.groupByKey()
    val value9: RDD[(String, List[(String, Int)])] = value8.map(
      i => {
        (i._1, i._2.toList.sortBy(i => i._2)(Ordering[Int].reverse).take(10))
      }
    )
    value9.collect().foreach(println)

    println(longs.toList)

    sc.stop()
  }
}

//所有参数的样例类
case class UserAction(
                       data: String, //用户点击行为的日期
                       user_id: String, //用户的ID
                       session_id: String, //Session的ID
                       page_id: String, //某个页面的ID
                       action_time: String, //动作的时间点
                       search_keyword: String, //用户搜索的关键词
                       click_category_id: String, //点击某一个商品品类的ID
                       click_product_id: String, //某一个商品的ID
                       order_category_ids: String, //一次订单中所有品类的ID集合
                       order_product_ids: String, //一次订单中所有商品的ID集合
                       pay_category_ids: String, //一次支付中所有品类的ID集合
                       pay_product_ids: String, //一次支付中所有商品的ID集合
                       city_id: String //城市 id
                     )

//top10的样例类
case class ExampleTop10(
                         var product_id: String, //品类id
                         var clickCount: Long, //订单点击次数
                         var orderCount: Long, //下单次数
                         var payCount: Long //支付次数
                       )


