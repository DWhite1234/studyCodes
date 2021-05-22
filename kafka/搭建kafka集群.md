# 搭建集群
## 1.搭建zookeeper集群
## 2.搭建kafka
    1.安装解压
    2.配置环境变量
    3.修改配置文件server.properties
        1.修改broker.id=0
        2.修改文件存放目录log.dirs=/opt/module/kafka/datas
        3.修改连接Zookeeper集群地址
        zookeeper.connect=hadoop102:2181,hadoop103:2181,hadoop104:218
    4.分发,同时修改各个节点上的broker.id
    5.启动测试
