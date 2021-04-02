# 新建表
create table newTable(
id int,
province_name varchar(50));

insert into newTable VALUES(1,'上海'),(2,'南京')

insert into newTable(id,province_name) values(3,'无锡')

update newTable set id=5 where province_name='无锡'

delete from newTable where id=5