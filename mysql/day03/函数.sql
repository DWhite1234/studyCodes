-- 函数分类
-- 	单行函数
-- 		字符串函数
				CONCAT(s1,s2)-->连接S1,S2,......,Sn为一个字符串
				CONCAT_WS(分隔符, S1,S2,......,Sn)-->用分隔符链接成一个整个字段
				UPPER()-->转大写
				LOWER()-->转小写
				TRIM()-->去除前后空格
-- 		数学函数
				ABS()-->返回绝对值
				CEIL()-->向下取整
				Floor()-->向上取整
				MOD(a,b)-->a/b 的模
				RAND()-->返回0-1的随机值
				ROUND(x,2)-->对x四舍五入,同时保留2位小数
				SQRT()-->返回平方根
				POW(X,Y)-->返回x的y次方值
-- 		日期函数
				CURRENT_DATE()-->返回当前日期2021-03-31
				CURRENT_TIME()-->返回当前时间18:20:06
				NOW()-->返回当前日期时间2021-03-31 18:20:06
				YEAR(date(日期类型))-->获取日期的年份
				DATE_ADD(NOW(),INTERVAL 1 year)-->返回当前日期加一年/月/日的值,数字可以为负值
				DATEDIFF(date1,date2)-->返回两个日期的差值
				
				# select DATEDIFF(NOW(),DATE_ADD(NOW(),INTERVAL 1 year))# -365
-- 		流程函数
				IFNULL(value1,value2)-->如果是不为null返回value1,如果为null返回value2
-- 				相当于if else if else if
				CASE
					WHEN expr THEN expr
					WHEN expr THEN expr
					WHEN expr THEN expr
					[ELSE resultn]
				END
-- 				相当于switch case
				CASE 字段
					WHEN 固定值 THEN expr
					WHEN 固定值 THEN expr
					ELSE expr
				END

-- 		其他函数
-- 	分组函数
			SUM()-->求和
			AVG()-->求平均值
			MAX()-->求最大值
			MIN()-->求最小值
			COUNT(1)-->满足条件的行数(数据条数),比起count(*)效率高