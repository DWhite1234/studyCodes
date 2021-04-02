-- DQL的关键字以及执行顺序,和聚合函数
SELECT # 5.查询结果
FROM    # 1.确定表
WHERE   # 2.过滤表数据
GROUP BY # 3.分组
HAVING  # 4.过滤聚合函数
ORDER BY # 6.将结果排序  asc(升序,默认值)|desc(降序)
LIMIT    # 7.将结果分页,或截取


常用的五种聚合函数:
	MAX(字段):求最大值
	MIN(字段):求最小值
	COUNT(字段):满足条件的数据条数
	AVG(字段):求平均值
	SUM(字段):求和





注意点:
	1. GROUP BY,SELECT:
		当使用了GROUP BY之后,select 后面只能跟聚合函数或者分组字段
		
	2. limit 1:
			相当于limit 0 ,1 取第一位数据

  3. 使用关键字distinct进行select去重