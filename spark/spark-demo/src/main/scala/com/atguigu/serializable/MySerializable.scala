package com.atguigu.serializable

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object MySerializable {
  def main(args: Array[String]): Unit = {
    /*
      1.spark天生具备 闭包检查
      所以只要算子中使用了外部变量,而这个变量所属类没有进行序列化就会报错

      2.只要是自定义的类就应该序列化
        2.1:序列化的两种方式
          1.样例类 case Person()
          2.实现serializable接口  xx extends Serializable

      3.spark推荐使用kryo序列化
      使用kryo序列化也必须显示的继承Serializable或是样例类
     */
    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    //怎么使用kryo序列化
    //添加kryo序列化设置
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    //注册使用该实例化的类
      .registerKryoClasses(Array(classOf[Student]))
    val sc = new SparkContext(conf)

    val value: RDD[Nothing] = sc.makeRDD(List())
    val student = new Student
    value.foreach(i=>student.name)
    sc.stop()

  }
}

case class Student(){
  var name:String= "zs"
}


