
##创建hive元数据库hive，并指定utf-8编码格式
mysql>create database metastore DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
 
 
##修改已存在的hive元数据库，字符编码格式为utf-8
mysql>alter database hive character set utf8;


##查看元数据库字符编码格式
mysql>show variables like 'character_set_database';

# 修改字段注释字符集
alter table COLUMNS_V2 modify column COMMENT varchar(256) character set utf8;

# 修改表注释字符集
alter table TABLE_PARAMS modify column PARAM_VALUE varchar(4000) character set utf8;

# 修改分区表参数
alter table PARTITION_PARAMS modify column PARAM_VALUE varchar(4000) character set utf8;

# 修改分桶表参数
alter table PARTITION_KEYS modify column PKEY_COMMENT varchar(4000) character set utf8;

# 修改索引注解
alter table INDEX_PARAMS modify column PARAM_VALUE varchar(4000) character set utf8;
