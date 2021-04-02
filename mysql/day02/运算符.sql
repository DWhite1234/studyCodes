分类:
	1.算数运算符
		+,-,*,/
	2.比较运算符
		>,>=,<,<=,<>(不等于),=
		
		注意mysql中没有==运算符
		
	3.逻辑运算符
		&& :and
		|| :or
		!	:not
		
		注意:
			使用not 的时候注意 and-->or,or-->and
			
			例如: a不等于c,且b不等于c
			 不使用 not: a<>c and b <> c
			 使用not	:  not(a=c or b =c)
			 
模糊查询 like:
	配合  %,和''使用
	例如:以A开头
	....like 'A%'	
	
集合查询(效率低)  in:
	例如:a(不)是a,b,c集合中的一个		
		...a (not) in (a,b,c)
	
判断是否是null:
		.....a is null
	