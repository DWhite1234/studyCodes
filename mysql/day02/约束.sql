/*
1.主键约束:主键唯一,不为null
2.非空约束:不能为null
3.unique约束:唯一
4.外键约束:引用主表主键
5.default 默认约束:给定默认值
*/
create table class(
	id int(10) auto_increment,
	primary key(id)
)

create table if not exists testtable(
-- 主键约束,自增
	id int(11) auto_increment,
-- 	唯一约束
	tb_name varchar(30) unique,
-- 	默认约束
	tb_age datetime default now(),
-- 	非空约束
	tb_height int(10) not null,
-- 	非空约束,外键约束
	class_id int(10) not NULL,
	primary key(id),
	foreign key(class_id) references class(id) 

)



/*
注意点:
	1.有外键约束时,插入数据必须先插入主表(外键引用的表)数据,在插入从表(外键所在表)数据
	2.删除数据时,必须先删除从表数据,再删除主表数据

*/