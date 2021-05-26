package com.atguigu.day07

import scala.collection.mutable
import scala.collection.mutable.Queue

object 队列 {
  def main(args: Array[String]): Unit = {
    val queue: mutable.Queue[Int] = Queue(1, 2, 3, 4, 5, 6)
    println(queue)
    //进队
    queue.enqueue(7)
    println(queue)
    //出队
    queue.dequeue()
    println(queue)
  }
}
