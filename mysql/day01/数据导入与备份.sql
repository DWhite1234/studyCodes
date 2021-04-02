# 数据库备份
mysqldump -hlocalhost -P3306 -uroot -p123456 --databases mydb > e:\test.sql

# 注意点
# 1.-P3306 P是大写
# 2.--databases 要加s
# 3.e:\test.sql 输出文件必须是.sql结尾


# 数据导入,cmd中测试可用
source e:/test.sql
