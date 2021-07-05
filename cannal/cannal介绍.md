# 1.诞生背景
阿里用于异地机房数据同步

# 2.怎么实现异地机房数据同步
借用mysql的利用binlog实现主从复制思想

mysql主从复制原理:
![](/pictures/mysql主从复制原理.png)
1.什么是binlog
binlog是mysql的最重要的二进制文件,他记录了所有的DDL和DML(除去数据查询语言),以事件的形式记录,还包含了执行sql所小号的时间,binlog是事务安全型的

注意:开启binlog会损耗mysql 1%的性能,默认关闭
2.主从复制原理
    1.mysql master主库会将数据改变的记录写进binlong二进制文件中
    2.slave从库访问binlog,将binlog拷贝到自身的中继日志中
    3.salve从库读取binlog,将数据同步到自身数据库中 


cannal的工作原理:
    将自身伪装成mysql的slave从库,读取binlog文件,拷贝数据    


# 3.binlog 的分类设置

statement:
    保存所有的写操作语句
    优点:节省空间
    缺点:如果写操作中包含随机数,日期之类的每次执行结果都不一致的,就会导致数据不一致
row:
    保存数据本身
    优点:数据绝对一致
    缺点:占用空间大
mixed:
    statement的优化升级版,当语句中包含随机数之类的不确定值时,会只用row来保存,否则使用statement保存
    优点:节省空间,同时兼顾了一定程度上的数据一致性
    缺点:在某些极端情况下还是会出现不一致的情况
