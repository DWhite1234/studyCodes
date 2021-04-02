# 创建库,多次执行会报错(数据库已经存在)
create database newDB;

create database if not exists newDB

#查询库
show databases;

# 删除库(多次执行会报错)
drop database newDB

drop database if exists newDB

# 创建表
create table testTable(
	id int(10),
	name varchar(20)
)

# 修改表名
alter table testTable rename newTestTable

# 增加一列
alter table newTestTable add birth dateTime;

# 修改列数据类型
alter table newtesttable modify id bigint;

# 修改列名和数据类型
alter table newtesttable change id num int(10)

# 删除一列
alter table newtesttable drop num;

# 删除表
drop table newtesttable

# 展示表结构
desc newtable;

show create table newtable