1.进入到/opt/module/sqoop/conf目录，重命名配置文件
mv sqoop-env-template.sh sqoop-env.sh

2.修改配置文件
vim sqoop-env.sh

export HADOOP_COMMON_HOME=/opt/module/hadoop-3.1.3
export HADOOP_MAPRED_HOME=/opt/module/hadoop-3.1.3
export HIVE_HOME=/opt/module/hive
export ZOOKEEPER_HOME=/opt/module/zookeeper-3.5.7
export ZOOCFGDIR=/opt/module/zookeeper-3.5.7/conf

3.拷贝JDBC驱动
cp mysql-connector-java-5.1.48.jar /opt/module/sqoop/lib/

4.验证Sqoop
sqoop help

结果:
Available commands:
  codegen            Generate code to interact with database records
  create-hive-table     Import a table definition into Hive
  eval               Evaluate a SQL statement and display the results
  export             Export an HDFS directory to a database table
  help               List available commands
  import             Import a table from a database to HDFS
  import-all-tables     Import tables from a database to HDFS
  import-mainframe    Import datasets from a mainframe server to HDFS
  job                Work with saved jobs
  list-databases        List available databases on a server
  list-tables           List available tables in a database
  merge              Merge results of incremental imports
  metastore           Run a standalone Sqoop metastore
  version            Display version information


5.测试Sqoop是否能够成功连接数据库
sqoop list-databases --connect jdbc:mysql://hadoop102:3306/ --username root --password 000000

预期输出:
information_schema
metastore
mysql
oozie
performance_schema
