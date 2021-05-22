# phoenix shell操作
##1.视图映射
<!-- 创建视图 -->
create view "test"(id varchar primary key,"info1"."name" varchar, "info2"."address" varchar);
<!-- 删除视图 -->
drop view "test";


注意点:
    视图是只读的,只能用来查询,无法对源数据进行修改
##2.表映射
<!-- 创建映射表 -->
create table "test"(id varchar primary key,"info1"."name" varchar, "info2"."address" varchar) column_encoded_bytes=0;

注意点:
    1.表映射可以修改源数据,而且删除映射表也会删除hbase中的被映射的表
    2.表映射时，不能使用列名编码,需将column_encoded_bytes设为0
    3.表映射时,当HBase中有数字类型的字段时，会出现解析错误的现象。Phoenix提供无符号类型unsigned_int，unsigned_long,如果需要考虑负数,则需要自定义函数,参考:https://phoenix.apache.org/udf.html