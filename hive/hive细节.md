# 1.metastore和hiveserver2有什么区别
相同点:
    1.都支持多客户端远程访问数据库
不同点:
    1. metastore通过元数据访问数据库
    hiveserver2通过JDBC或ODBC等方式访问数据库
    2.metastore方式访问数据库,不需要url,username,password等参数
    hiveserver2需要配置url,username,password这些

# 2.hive -e/f
hive -e直接执行sql
hive -e "select * from student"

hive -f 执行sql脚本
hive -f /opt/module/test.sql

# 3.退出hive窗口
quit;


# 4.历史命令
linux 的历史命令
history

beenline的历史命令
根目录下 .beenline/history

# 5.日志查看
查看/opt/module/hive/conf/hive-log4j.properties中
hive.log.dir=/opt/module/hive/logs

# 6.set
set;可以查看所有的配置信息

使用set设置属性,仅在本次启动生效
set mapreduce.job.reduces=10;

使用set查看属性
set mapreduce.job.reduces;

# 7.分区字段
分区字段一定不在表字段中,属于伪劣

# 8.排序
sorted by 分区内排序
distributed by 将数据分区
distributed by一般结合sorted by使用

distributed by的分区规则是分区字段进行hash,对分区数进行取模
distributed by必须卸载sorted by之前

cluster by 是同时具有sorted by和distributed by的功能,只不过是sorted by和distributed by
针对的是同一个字段,同时只能进行升序排序,不能指定降序排序

# 9.get_json_object函数
get_json_object("[{"name":"大郎","sex":"男","age":"25"},{"name":"西门庆","sex":"男","age":"47"}]","$[0]")

get_json_object("["person":{"name":"大郎","sex":"男","age":"25"},"person1":{"name":"西门庆","sex":"男","age":"47"}]","$.person")
取出json字符串中的json对象

