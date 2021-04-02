-- 子查询返回的结果类型:
	-- 单行单列,多用来比较
	-- 多行单列
			多用于比较任一(any),或者全部(all)
			例如:大于任一一个人的成绩
				SELECT *
				FROM grade
				where score>any(
					select score
					from student
				)
			例如:大于所有人的成绩	
				SELECT *
				FROM grade
				where score>all(
					select score
					from student
				)
	-- 多行多列

-- 子查询出现的地方
		where之后、having之后、from之后、from之前(select 中)
	