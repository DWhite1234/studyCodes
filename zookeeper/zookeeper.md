# 1.配置参数
1.心跳时间2s,服务端与客户端通信间隔
tickTime=2000
2.初始化时间限制,follow与leader连接超时间(心跳时间的倍数)
initLimit=10(10*2=20s)
3.同步时限,超过时间leader认为follow死掉(心跳时间倍数)
syncLimit=5
4.数据保存目录
dataDir=/opt/module/zookeeper-3.5.7/zkData
5.客户端与服务端通信的端口
clientPort=2181

# 2.分布式搭建
1.在/opt/module/zookeeperxxx/ 创建存储数据的目录
cd /opt/module/zookeeper-3.5.7
mkdir zkData
2.创建文件编辑每个服务器的名字
cd /opt/module/zookeeper-3.5.7/zkData
touch myid
3.修改配置文件名字
mv zoo_sample.cfg zoo.cfg
4.增加配置
server.1=hadoop101:2888:3888
server.服务器id=服务器主机名:(follower和leader通信端口):(选举机制通信端口)
5.增加环境变量
 ZOOKEEPER_HOME
 export ZOOLEEPER_HOME = /opt/module/zookeeper-3.5.7
 export PATH=$PATH:$ZOOKEEPER/bin

 source /etc/profile
 6.启动zookeeper集群
 zkServer.sh start/stop/status
 zkCli.sh(启动客户端)

 # 3.常用命令
 ls
 ls -w :监视子节点的变化
 ls -s :查看子目录+结构体
 create
 create -s :创建带序列的节点
 create -e :创建临时节点,客户端退出销毁
 set
 get
 get -w :监视节点内容的变化
 get -s :查看节点值+节点结构体
 stat   :单独查看结构体
 delete :删除空目录
 deleteall :递归删除