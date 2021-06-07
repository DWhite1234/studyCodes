# 1.spark_sql 和 hive on spark的区别
spark SQL:hive 只是相当于数据存储,spark负责解析优化SQL,spark底层使用df或ds执行
hive on spark:hive既负责存储数据有负责sql解析优化,spark只是计算引擎,使用rdd进行计算


# RDD,dataFrame,dataSet的区别
RDD:只关心数据本身
dataFrame:只关心数据的结构,不关心数的类型
    有行有列,类似于数据库的表
dataSet:面向对象访问数据

注意:
    1.RDD只会按照顺序执行,比如先进行join在进行filter
但是df会先进行优化,先执行filter,在进行join,减少了数据量,大大提升了效率   
    2.df是ds的特例, type DataFrame = DataSet[Row]
    3.版本发展,RDD(1.0)=>dataFrame(1.3)=>dataSet(1.6),后期ds可能会成为唯一的API
相同点:
    1.都是分布式弹性数据集的一种
    2.都有惰性机制,只有遇到行动算子才会真正开始执行
    3.有大量的公共函数
    4.都能够自动缓存运算
    5.都有分区的概念


# spark的特点
1.易整合:无缝的整合了SQL和spark编程
2.统一的数据访问方式:使用相同的方式连接不同的数据源
3.兼容Hive:能够在已有的仓库上直接运行sql或hql
4.标准的数据连接:通过JDBC(java连接数据库) 和 ODBC(c连接数据库)

# sparkSession
旧版本:
    SQLContext:负责spark的SQL查询
    HiveContext:负责Hive的SQL查询

新版本:
    sparkSession整合SQLContext 和HiveContext


# df创建的三种方式
1.基于数据源直接创建
val df1: DataFrame = ss.read.json("E:\\studyCodes\\spark\\spark-SQL\\data\\user.json")
df1.show()

2.从已有数据源创建sql风格,必须先创建视图
    1.用数据源创建视图(视图可以创建全局试图,也可以创建临时视图)
    df1.createTemplate("user")
    2.用sparksql对象来执行sql
    ss.sql("select * from user")  //临时视图
    ss.sql("select * from global_temp.user")//全局视图

3.从已有数据源创建dsl风格,不需要创建视图
    结构已经提供好了,只需要填写内容即可
    df1.select("*").where("age > 18 ")
