package com.atguigu.dependency

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object stage {
  def main(args: Array[String]): Unit = {
    /*

    RDD任务切分中间分为：Application、Job、Stage和Task
    （1）Application：初始化一个SparkContext即生成一个Application；
    （2）Job：一个Action算子就会生成一个Job；
    （3）Stage：Stage等于宽依赖的个数加1；
    （4）Task：一个Stage阶段中，最后一个RDD的分区个数就是Task的个数。
    注意：Application->Job->Stage->Task每一层都是1对n的关系。

     */

    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //textFile,flatMap,map算子全部是窄依赖,不会增加stage阶段
    val lineRDD: RDD[String] = sc.textFile("E:\\studyCodes\\spark\\spark-demo\\data\\1.txt")
    val flatMapRDD: RDD[String] = lineRDD.flatMap(_.split(" "))
    val mapRDD: RDD[(String, Int)] = flatMapRDD.map((_, 1))

    //reduceByKey算子会有宽依赖,stage阶段加1，2个stage
    val resultRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_ + _)

    //Job：一个Action算子就会生成一个Job，2个Job
    //job0打印到控制台
    resultRDD.collect().foreach(println)
    //job1输出到磁盘
    resultRDD.saveAsTextFile("out")

    //阻塞线程,方便进入localhost:4040查看
    Thread.sleep(Long.MaxValue)

    /*
    结构:
                      Job0
    Application==>                  stage0
                      Job1 =======>                     Task0
                                    stage1 ==========>
                                                        Task0
     */


    /*

    源码解析:

     1.dagScheduler.runJob
     2.submitJob
     3.eventProcessLoop.post(JobSubmitted(...))
     4.eventQueue.put(event)
     5.onReceive(event)
     6.doOnReceive
        进行了模式匹配
        case JobSubmitted(jobId, rdd, func, partitions, callSite, listener, properties) =>
          dagScheduler.handleJobSubmitted(jobId, rdd, func, partitions, callSite, listener, properties)

        case MapStageSubmitted(jobId, dependency, callSite, listener, properties) =>
          dagScheduler.handleMapStageSubmitted(jobId, dependency, callSite, listener, properties)

        case StageCancelled(stageId, reason) =>
          dagScheduler.handleStageCancellation(stageId, reason)

        .....
        7.dagScheduler.handleJobSubmitted(在该方法后续进行了stage切分)
            8.finalStage = createResultStage(finalRDD, func, partitions, jobId, callSite)
            9.val parents = getOrCreateParentStages(rdd, jobId)在此方法中进行了stage切分
        10.finalStage.setActiveJob(job)(将切分的stage挂在job下)

        11.val tasks: Seq[Task[_]] = try {
              ....
              case stage: ShuffleMapStage =>
                .....
                partitionsToCompute.map {... }

              case stage: ResultStage =>
              .....
                partitionsToCompute.map {... }
          }
             12.partitionsToCompute.map
             13.findMissingPartitions(抽象方法,有两个实现类ResultStage和ShuffleMapStage)
                 在这两个实现类中都有共同一段代码
                 (0 until job.numPartitions) ==> task的个数等于阶段的分区数,阶段的分区数等于最后一个rdd的分区数


     */


    sc.stop()
  }
}
