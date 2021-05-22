# flume连接kafka配合flume连接器

＃命名此代理上的组件
a1.sources  =  r1 
a1.sinks  =  k1 
a1.channels  =  c1

<!-- flume连接器 -->
a1.sources.r1.interceptors = i1
a1.sources.r1.interceptors.i1.type = com.atguigu.flume.MyFlumeInterceptor$MyFlumeBuilder



＃描述/配置源
a1.sources.r1.type  =  netcat 
a1.sources.r1.bind  =  hadoop101 
a1.sources.r1.port  =  44444

＃描述接收器
a1.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink
<!-- 指定自定义拦截器中没有的默认值,flume中会自动识别拦截器中的topic -->
a1.sinks.k1.kafka.topic = second
a1.sinks.k1.kafka.bootstrap.servers = hadoop101:9092
a1.sinks.k1.kafka.flumeBatchSize = 20
a1.sinks.k1.kafka.producer.acks = -1
a1.sinks.k1.kafka.producer.linger.ms = 1

＃使用一个通道将事件缓存在内存
a1.channels.c1.type  = memory
a1.channels.c1.capacity  =  1000 
a1.channels.c1.transactionCapacity  =  100

＃将源和接收器绑定到通道
a1.sources.r1.channels  =  c1 
a1.sinks.k1.channel  =  c1
