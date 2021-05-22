# Hbase的架构以及原理
## 1.基础架构
    1.Master------>namenode
        对于表的操作：create, delete, alter
        对于RegionServer的操作：分配regions到每个RegionServer,监控每个RegionServer
    2.zookeeper
    借助zookeeper实现分布式以及高可用(临时节点)
    3.Region Server
        对于数据的操作：get, put, delete；
        对于Region的操作：splitRegion、compactRegion。
    4.HDFS
    数据存储
