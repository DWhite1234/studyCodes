# 1.delete用法
常规用法:单表删除
	delete from person where id=7
进阶用法:多表删除
	delete p1 from person p1,person p2
	where p1.email=p2.email and p1.id > p2.id	

