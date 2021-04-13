# 1.准备虚拟机
	1.配置静态ip
		1.1 编辑虚拟机网络,更改NAT的子网ip和网关
		1.2 vim /etc/sysconfig/network-scripts/ifcfg-ens33
			将ip获取方式更改为static
			新增ip地址 IPADDR=192.168.10.100
			新增网关   GATEWAY=192.168.10.2
			新增DNS1   DNS1=192.168.10.2
		1.3 重启机器(单独修改网络可以只重启网络service network restart)	
	2.修改hostname
		vim /etc/hostname
	3.配置映射
		vim /etc/hosts		
# 2.克隆虚拟机,同时分别修改ip和hostname	

# 3.安装jdk和hadoop,以及配置环境变量
	1.安装软件
		在opt目录下创建两个目录module(安装包),software(压缩包)
		把software下的压缩文件解压到module下
		tar -zxvf xxxx.tar.gz -C /opt/module/
	2.配置环境变量
		1.创建自己的环境变量文件
		vim /etc/profile.d/my_env.sh
		2.添加环境变量
		#JAVA_HOME
		export JAVA_HOME=/opt/module/jdk.....
		export PATH=$PATH:$JAVA_HOME/bin

		#HADOOP_HOME
		export HADOOP_HOME=/opt/module/hadoop-3.1.3
		export PATH=$PATH:$HADOOP_HOME/bin
		export PATH=$PATH:$HADOOP_HOME/sbin

# 4.软件群发
	1.scp复制资源(不常用)

		拉取资源  主机@用户:资源路径                 目标路径
		scp -r hadoop101@zt:/opt/module/jdk...  /opt/module/

		发送资源  目标路径       主机@用户:资源路径
		scp -r /opt/module/  hadoop101@zt:/opt/module/jdk...	
	2.rsync(需要安装),差异文件复制,一般编写群发脚本

		rsync -av  资源路径 目标路径

# 5.配置hadoop配置文件,以及workders
	1.配置文件
		core-site.xml
		dfs-site.xml
		mapred-site.xml
		yarn-site.xml
	2.workers(hadoop映射)
		hadoop101
		hadoop102	
		hadoop103
	3.配置完后需要分发给所有的服务器			
# 6.集群启动并测试

	1.格式化
	hdfs namenode -format			
	2.启动NameNode
	start-dfs.sh
	3.启动yarn
	start-yarn.sh
	4.启动日志服务器
	mapred --daemon start historyserver
	5.检测启动情况
	jps
	6.使用hadoop提供的单点测试案例
	hadoop jar share/hadoop/mapreduce/hadoop..examples.../  wordcount  /input  /output
	7.访问
	9870,8088,19888端口,查看服务器是否正常运行
# 7.集群启动优化,检测优化
	1.配置ssh免密登录
		生成公钥
		ssh-keygen -t rsa 
		将公钥分发到其余的服务器上
		ssh-copy-id hadoop101
		ssh-copy-id hadoop102
		ssh-copy-id hadoop103

	注意点:
		发送环境变量时需要root权限,必须给root也配置ssh	
	2.编写群起,群停脚本
	3.编写群测脚本	
