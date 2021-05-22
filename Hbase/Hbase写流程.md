# Hbase写流程
<!-- 查找meta 表 -->
1.put 数据,访问zookeeper,请求meta所在的regionserver
2.zookeeper返回meta所在的regionserver
3.client访问相应的regionserv
4.对应的regionserver返回meta表
5.client将读取的meta表缓存起来,下次优先读取meta cache,如果找不到对应的文件,则重复上述流程
<!-- 开始写数据 -->
6.访问目标regionserver
7.先向WAL中写入数据,WAL将数据落盘
8.WAL向指定store的memstore缓存中写入数据
9.memstore将写入的数据进行排序,并且返回ack,如果ack返回失败,从WAL重新读取数据
10.memstore等到刷写时机,将数据写到HFile

# MemStore Flush时机
1.
(刷写上限)当region中某个memstore的大小达到hbase.hregion.memstore.flush.size（默认值128M）,其所在的region的所有的memstore都会开始刷写

(数据上限)当memstore的大小达到了
hbase.hregion.memstore.flush.size（默认值128M）* hbase.hregion.memstore.block.multiplier（默认值4）,会阻止继续往memstore写数据

2.
(刷写上限)当region server中memstore的总大小达到
java_heapsize(JVM堆内存)
*hbase.regionserver.global.memstore.size（默认值0.4）
*hbase.regionserver.global.memstore.size.lower.limit（默认值0.95），
region会按照其所有memstore的大小顺序（由大到小）依次进行刷写。直到region server中所有memstore的总大小减小到上述值以下。

(数据上限)当region server中memstore的总大小达到
java_heapsize
*hbase.regionserver.global.memstore.size（默认值0.4）
时，会阻止继续往所有的memstore写数据。

3.
(刷新时间间隔)到达自动刷写的时间，也会触发memstore flush。自动刷新的时间间隔由该属性进行配置hbase.regionserver.optionalcacheflushinterval（默认1小时）

