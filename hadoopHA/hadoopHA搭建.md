# hadoopHA hdfs+yarn自动故障转移
1.配置zookeeper分布式集群
2.配置HDFS-HA 集群
    1.opt目录下创建ha目录
    2.拷贝hadoop文件到ha目录下(注意文件的所属主和所属组)
    3.修改配置文件
    4.修改环境变量为当前的ha的hadoop,否则可能导致之前的集群崩溃
    5.分发文件
3.配置YARN-HA 集群
    1.修改配置文件    
4.启动
    1.启动jn
    hdfs --daemon start journalnode
    2.格式化nn1
    hdfs namenode -format
    3.启动nn1并且nn2,nn3同步nn1
    启动nn
    hdfs --daemon start namenode
    同步nn
    hdfs namenode -bootstrapStandby
    4.启动zookeeper集群
    zkServer.sh start
    5.初始化zkfc
    hdfs zkfc -formatZK
    6.启动hdfs
    start-dfs.sh
4.zkCli.sh查看当前active的hdfs和yarn是谁


注意点:
    nn连不上jn时,重连10次超时
在core-site.xml中添加属性,更改重连次数和重试间隔
<!-- NN连接JN重试次数，默认是10次 -->
<property>
  <name>ipc.client.connect.max.retries</name>
  <value>10</value>
</property>
<!-- 重试时间间隔，默认1s -->
<property>
  <name>ipc.client.connect.retry.interval</name>
  <value>1000</value>
</property>


