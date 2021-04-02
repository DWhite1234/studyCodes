-- 内连接(显示)
SELECT empno,ename
from emp
inner join dept 
on emp.deptno=dept.deptno

-- 内连接(隐式)
SELECT empno,ename
FROM emp,dept
where emp.deptno=dept.deptno

-- 左外连接
SELECT empno,ename
FROM emp
left JOIN dept
ON emp.deptno=dept.deptno

-- 右外连接
select empno,ename
from emp
right join dept
on emp.deptno=dept.deptno

-- 自连接(是一种思想,而不是一种具体的格式)
select e1.empno,e2.ename
from emp e1
inner join emp e2
on e1.mgr=e2.empno

-- 合并查询
 -- union all 不去重
 
	select empno,ename
	from emp
	union all
	select deptno,dname
	from dept

 -- union 去重 	
	select empno,ename
	from emp
	union
	select deptno,dname
	from dept
