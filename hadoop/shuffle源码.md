# shuffle全流程(combiner,partitioner)源码
*map写出之后开始*

1.mapper写出数据先进入partitioner类,对数据进行分区

2.进入collect方法(环形缓冲区)

    3.在环形缓冲区内kvmeta添加数据的索引信息(kvindex,partition,keystart,valstart)
    
4.map向环形缓冲区写出一行数据结束

5.当所有的数据全部写完之后,执行cleanup方法

6.mapPhase.complete()map阶段结束

7.map写入环形缓冲区结束,但是由于文件过小没有触发80%的溢写,但是map数据全部写完也会触发溢写

8.collector.flush()准备将缓冲区内的数据写入磁盘

9.sortAndSpill()在写入磁盘前先对索引进行排序(快拍)
    10.sorter.sort()快拍逻辑,排序完成后生成溢写文件spill0.out
    11.for (int i = 0; i < partitions; ++i) {....},循环写出到溢写文件
    多个分区同时溢写到一个溢写文件

12.mergeParts()合并溢写文件,归并排序,并生成溢写文件的索引文件file.out.index

*map之后的阶段结束*

*reduce之前开始*

1.初始化三个状态copy,sort,reduce

2.shuffleConsumerPlugin.init(shuffleContext)初始化

    3.scheduler = new ShuffleSchedulerImpl(..)获取所有的NumMapTasks

    4.merger = createMergeManager(context)

        5.return new MergeManagerImpl()初始化对应的内存和磁盘存储数据
            this.inMemoryMerger = createInMemoryMerger();
            this.inMemoryMerger.start();
            
            this.onDiskMerger = new OnDiskMerger(this);
            this.onDiskMerger.start();

6.rIter = shuffleConsumerPlugin.run()从各个mapTask上抓取数据,而不是mapTask把数据发过来
    eventFetcher.start()开始抓取数据

    eventFetcher.shutDown()结束抓取数据

    copyPhase.complete()copy阶段结束

    taskStatus.setPhase(TaskStatus.Phase.SORT)进入sort阶段


7.sortPhase.complete()sort阶段结束,合并,归并排序

8.runNewReducer()
     reducer.run(reducerContext)
     运行reduce
*reduce之前结束*        
