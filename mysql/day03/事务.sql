-- 开启事务
start transaction;
-- 提交事务
commit;
-- 回滚事务
rollback;

-- 注意点
1. commit和rollback不能同时使用,控制commit和rollback将会有java语句控制
2. mysql默认是自动提交事务,但是start transaction后,会自动关闭自动提交事务,需要手动提交
3. 事务的四大特征:原子性,一致性,隔离性,持久性
4. 事务的隔离级别:uncommitted(为提交的),committed(已提交的),repeatable read(可重复读的),serializable(串行化)
		工作中不会选择uncommitted(不安全),serializable(效率低),会在剩下的两种中来回切换