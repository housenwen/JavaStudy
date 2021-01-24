
 -- 单行子查询(> < >= <= = <>)
	-- 查询出高于10号部门的平均工资的员工信息
	SELECT * FROM emp WHERE sal>(SELECT AVG(sal) FROM emp WHERE deptno=10) AND deptno!=10;
 -- 多行子查询(in  not in any all)
	-- 查询出比10号部门任何员工薪资高的员工信息
	SELECT * FROM emp WHERE sal >ANY(SELECT sal FROM emp WHERE deptno=10) AND deptno!=10; 
	-- 多列子查询(实际使用较少)
	-- 和10号部门同名同工作的员工信息
	
	SELECT * FROM emp WHERE (ename,job) IN(SELECT ename,job FROM emp WHERE deptno=10)AND deptno!=10;
	-- 删除表格
	DROP TABLE emp;
	DROP TABLE dept;
	
CREATE TABLE emp(
	empno INT,
	ename VARCHAR(50),
	job VARCHAR(50),
	mgr	INT,
	hiredate DATE,
	sal	DECIMAL(7,2),
	comm DECIMAL(7,2),
	deptno INT
) ;

	INSERT INTO emp VALUES(7369,'SMITH','CLERK',7902,'1980-12-17',800,NULL,20);
	INSERT INTO emp VALUES(7499,'ALLEN','SALESMAN',7698,'1981-02-20',1600,300,30);
	INSERT INTO emp VALUES(7521,'WARD','SALESMAN',7698,'1981-02-22',1250,500,30);
	INSERT INTO emp VALUES(7566,'JONES','MANAGER',7839,'1981-04-02',2975,NULL,20);
	INSERT INTO emp VALUES(7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250,1400,30);
	INSERT INTO emp VALUES(7698,'BLAKE','MANAGER',7839,'1981-05-01',2850,NULL,30);
	INSERT INTO emp VALUES(7782,'CLARK','MANAGER',7839,'1981-06-09',2450,NULL,10);
	INSERT INTO emp VALUES(7788,'SCOTT','ANALYST',7566,'1987-04-19',3000,NULL,20);
	INSERT INTO emp VALUES(7839,'KING','PRESIDENT',NULL,'1981-11-17',5000,NULL,10);
	INSERT INTO emp VALUES(7844,'TURNER','SALESMAN',7698,'1981-09-08',1500,0,30);
	INSERT INTO emp VALUES(7876,'ADAMS','CLERK',7788,'1987-05-23',1100,NULL,20);
	INSERT INTO emp VALUES(7900,'JAMES','CLERK',7698,'1981-12-03',950,NULL,30);
	INSERT INTO emp VALUES(7902,'FORD','ANALYST',7566,'1981-12-03',3000,NULL,20);
	INSERT INTO emp VALUES(7934,'MILLER','CLERK',7782,'1982-01-23',1300,NULL,10);
	INSERT INTO emp VALUES(7981,'MILLER','CLERK',7788,'1992-01-23',2600,500,20);
	
	CREATE TABLE dept(
	deptno		INT,
	dname		VARCHAR(14),
	loc		VARCHAR(13)
	);

	INSERT INTO dept VALUES(10, 'ACCOUNTING', 'NEW YORK');
	INSERT INTO dept VALUES(20, 'RESEARCH', 'DALLAS');
	INSERT INTO dept VALUES(30, 'SALES', 'CHICAGO');
	INSERT INTO dept VALUES(40, 'OPERATIONS', 'BOSTON');
	-- 单行子查询(> < >= <= = <>)
	-- 查询出高于10号部门的平均工资的员工信息
	SELECT * FROM emp WHERE sal >(SELECT AVG(sal) FROM emp WHERE deptno=10 );
	-- 多行子查询(in  not in any all)
	-- 查询出比10号部门任何员工薪资高的员工信息
	SELECT * FROM emp WHERE sal > ANY(SELECT sal FROM emp WHERE deptno=10) AND deptno!=10;
	-- 多列子查询(实际使用较少)
	-- 和10号部门同名同工作的员工信息
	SELECT * FROM emp WHERE (ename,job)IN(SELECT ename,job FROM emp WHERE deptno=10) AND deptno!=10;
	-- select 后面接子查询	
	-- 获取员工的名字和部门的名字
	SELECT p.ename,d.dname FROM emp p,dept d WHERE p.deptno=d.deptno;
	-- from 后面接子查询
	-- 查询emp表中经理信息
	SELECT * FROM emp e,(SELECT DISTINCT mgr FROM emp) AS jingli WHERE e.empno=jingli.mgr; 
	-- where 后面接子查询	
	-- 薪资高于10号部门平均工资的所有员工信息
	SELECT * FROM emp WHERE sal>(SELECT AVG(sal) FROM emp WHERE deptno=10);
	-- group by 后面接子查询
	-- 有哪些部门的平均工资高于30号部门的平均工资	
	SELECT deptno, AVG(sal) AS bumen FROM emp GROUP BY deptno HAVING bumen > (SELECT AVG(sal) FROM emp WHERE deptno=30);
	-- 工资>JONES工资
	
	SELECT * FROM emp WHERE sal>(SELECT sal FROM emp WHERE ename='JONES');
	-- 查询与SCOTT同一个部门的员工
	SELECT * FROM emp WHERE deptno = (SELECT deptno FROM emp WHERE ename='SCOTT') AND ename!='SCOTT';
	-- 工资高于30号部门所有人的员工信息
	SELECT * FROM emp WHERE sal>(SELECT MAX(sal) FROM emp WHERE deptno=30);
	-- 查询工作和工资与MARTIN完全相同的员工信息
	SELECT job,sal FROM emp WHERE ename='MARTIN';
	SELECT * FROM emp WHERE (job,sal)IN(SELECT job,sal FROM emp WHERE ename='MARTIN');
	-- 有两个以上直接下属的员工信息
	-- 得出两个以上的经理信息
	SELECT mgr,COUNT(mgr) FROM emp GROUP BY mgr HAVING COUNT(*) >2;
	-- 得出两个以上直接下属的员工信息
	SELECT * FROM emp WHERE empno IN (SELECT mgr FROM emp GROUP BY mgr HAVING COUNT(mgr) >2);
	-- 老师的方法
	SELECT * FROM emp e1 WHERE e1.empno IN (SELECT e2.mgr FROM emp e2 GROUP BY e2.mgr HAVING COUNT(*)>2);
	
	-- 查询员工编号为7788的员工名称,员工工资,部门名称,部门地址
	SELECT e.*,d.loc FROM emp e,dept d WHERE e.empno=7788 AND e.deptno=d.deptno;
	-- 1. 查询出高于本部门平均工资的员工信息-
	SELECT *,AVG(sal) AS 平均工资 FROM emp GROUP BY deptno;
	SELECT * FROM emp e WHERE sal > (SELECT AVG(sal) AS 平均工资 FROM emp d GROUP BY deptno HAVING e.deptno=d.deptno);
	-- 方式二:
	-- SELECT * FROM emp e1 WHERE e1.sal > (SELECT AVG(e2.sal) FROM emp e2 WHERE e1.deptno=e2.deptno  GROUP BY e2.deptno);
	-- 列出达拉斯加工作的人中,比纽约平均工资高的人
	SELECT AVG(sal) FROM emp e INNER JOIN dept d ON e.deptno=d.deptno WHERE d.loc='NEW YORK';
	
	SELECT * FROM emp e INNER JOIN dept d ON e.deptno=d.deptno WHERE  d.loc='DALLAS' 
	AND sal>(SELECT AVG(sal) FROM emp e INNER JOIN dept d ON e.deptno=d.deptno WHERE d.loc='NEW YORK');
	-- 方法二
	SELECT * FROM emp WHERE deptno = (SELECT deptno FROM dept WHERE loc='DALLAS')
	AND sal > (SELECT AVG(sal) FROM emp WHERE deptno = (SELECT deptno FROM dept WHERE loc='NEW YORK'));
	-- 查询7369员工编号,姓名,经理编号和经理姓名
	SELECT mgr FROM emp WHERE empno=7369;
	SELECT ename FROM emp WHERE empno =(SELECT mgr FROM emp WHERE empno=7369);
	
	SELECT empno,ename,(SELECT mgr FROM emp WHERE empno=7369) AS 经理编号 
	,(SELECT ename FROM emp WHERE empno =(SELECT mgr FROM emp WHERE empno=7369)) AS 经理姓名 FROM emp WHERE empno=7369;
	
	SELECT e1.empno,e1.ename,e1.mgr,mgrtable.ename FROM emp e1,emp mgrtable WHERE e1.mgr = mgrtable.empno AND e1.empno=7369; 
	-- 查询出各个部门薪水最高的员工所有信息
	SELECT * FROM emp  WHERE sal=MAX(sal) GROUP BY deptno;
	
	SELECT * FROM emp e WHERE sal = (SELECT MAX(sal) AS 最高薪水 FROM emp e2 WHERE e.deptno=e2.deptno GROUP BY deptno );
	
	-- SELECT * FROM emp e1 WHERE e1.sal = (SELECT MAX(sal) FROM emp e2 WHERE e1.deptno = e2.deptno GROUP BY deptno);
	
	--面试题
	CREATE TABLE test(
	  NAME CHAR(20),
	  kecheng CHAR(20),
	  fenshu CHAR(20)
	);

	INSERT INTO test VALUES
	('张三','语文',81),
	('张三','数学',75),
	('李四','语文',76),
	('李四','数学',90),
	('王五','语文',81),
	('王五','数学',82);
	
	-- 请用一条Sql语句查处分数大于80的学生
	SELECT DISTINCT NAME FROM test WHERE fenshu>80;
	
	