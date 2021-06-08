# HBase使用
1.安装解压
2.修改配置文件
    1.hbase-env.sh修改内容：
    <!-- 不使用自带的zookeeper,单机模式 -->
    export HBASE_MANAGES_ZK=false
    2.hbase-site.xml修改内容
    <property>
        <name>hbase.rootdir</name>
        <value>hdfs://hadoop101:8020/hbase</value>
    </property>
    <property>
        <name>hbase.cluster.distributed</name>
        <value>true</value>
    </property>
    <property>
        <name>hbase.zookeeper.quorum</name>
        <value>hadoop101,hadoop102,hadoop103</value>
    </property>
    3.regionservers
    <!-- 添加集群节点 -->
    hadoop102
    hadoop103
    hadoop104
3.配置高可用
    在conf目录下创建backup-masters,并加入作为备用master的节点,分发

4.分发启动
    1.单点启动
        hbase-daemon.sh start master
        hbase-daemon.sh start regionserver
    2.群起,需要启动zk
        start-hbase.sh
        stop-hbase.sh 
    3.页面
    http://hadoop101:16010
               
    

