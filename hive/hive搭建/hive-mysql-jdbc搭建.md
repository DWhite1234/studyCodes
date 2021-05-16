# 1.hive jdbc+mysql方式搭建
## 1.安装hive,配置环境变量,初始化derby数据库
1.安装hive..... 
2.环境变量.....
3.schematool -dbType -initSchema derby
## 2.安装mysql
1.检查是否安装过mysql
rpm -qa | grep mysql
rpm -qa | grep mariadb
如果有删除
sudo rpm -e --nodeps xxxxxx

2.解压,安装mysql依赖
tar -xvf mysql.....bund.tar (注意是tar包,不是tar.gz包)
rpm -ivh xxxxx-common-xxx
rpm -ivh xxxxx-libs-xxxx
rpm -ivh xxxxx-libs-compat-xxx
rpm -ivh xxxxx-client-xxxx
rpm -ivh xxxxx-server-xxx(这里有可能报错,缺少libaio依赖)
yum install libaio

3.删除内容
查看
cat /etc/my.cnf
[mysqld]
datadir=/var/lib/mysql

删除datadir下所有内容
cd /var/lib/mysql
sudo rm -rf *(此处注意当前的目录位置,不要误杀)

4.初始化数据库
sudo mysqld --initialize --user=mysql

5.查看临时密码
cat /var/log/mysqld.log

6.启动mysql服务
systemctl start mysqld
systemctl status mysqld

7.登录mysql,修改初始密码
mysql -uroot -p'复制的密码'
set password = password('123456')

8.修改user表中的root的host为%,允许任意人连接
9.刷新权限(重点记住)
flush privileges;

## 3.更改hive的元数据库为mysql
1.拷贝驱动
cp /opt/software/mysql-connector-java-5.1.37.jar /opt/module/hive-3.1.2/lib
2.修改配置文件
vim hive-site.xml
```xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <property>
        <name>javax.jdo.option.ConnectionURL</name>
        <value>jdbc:mysql://hadoop102:3306/metastore?useSSL=false</value>
    </property>

    <property>
        <name>javax.jdo.option.ConnectionDriverName</name>
        <value>com.mysql.jdbc.Driver</value>
    </property>

    <property>
        <name>javax.jdo.option.ConnectionUserName</name>
        <value>root</value>
    </property>

    <property>
        <name>javax.jdo.option.ConnectionPassword</name>
        <value>000000</value>
    </property>

    <property>
        <name>hive.metastore.warehouse.dir</name>
        <value>/user/hive/warehouse</value>
    </property>

    <property>
        <name>hive.metastore.schema.verification</name>
        <value>false</value>
    </property>

    <property>
    <name>hive.server2.thrift.port</name>
    <value>10000</value>
    </property>

    <property>
        <name>hive.server2.thrift.bind.host</name>
        <value>hadoop102</value>
    </property>

    <property>
        <name>hive.metastore.event.db.notification.api.auth</name>
        <value>false</value>
    </property>
    
    <property>
        <name>hive.cli.print.header</name>
        <value>true</value>
    </property>

    <property>
        <name>hive.cli.print.current.db</name>
        <value>true</value>
    </property>
    <!-- hiveserver2的高可用参数，开启此参数可以提高hiveserver2的启动速度 -->
    <property>
        <name>hive.server2.active.passive.ha.enable</name>
        <value>true</value>
    </property>
</configuration>
```

3.登录mysql,新建元数据库metastore,并且初始化元数据库
登录mysql...
新建元数据库...
schematool -dbType -initSchema mysql -verbose

## 4.jdbc连接hive
1.自带脚本启动hiveserver2服务
hiveserver2
2.启动beeline客户端
bin/beeline -u jdbc:hive2://hadoop101:10000 -n zt