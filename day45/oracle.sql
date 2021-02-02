-- 部门表
create table dept(
                     deptno int primary key auto_increment, -- 部门编号
                     dname varchar(14) ,	  -- 部门名字
                     loc varchar(13)   -- 地址
) ;
-- 员工表
create table emp(
                    empno int primary key auto_increment,-- 员工编号
                    ename varchar(10), -- 员工姓名										-
                    job varchar(9),	-- 岗位
                    mgr int,	 -- 直接领导编号
                    hiredate date, -- 雇佣日期，入职日期
                    sal int, -- 薪水
                    comm int,  -- 提成
                    deptno int not null, -- 部门编号
                    foreign key (deptno) references dept(deptno)
);
insert into dept values(10,'财务部','北京');
insert into dept values(20,'研发部','上海');
insert into dept values(30,'销售部','广州');
insert into dept values(40,'行政部','深圳');
insert into emp values(7369,'刘一','职员',7902,'1980-12-17',800,null,20);
insert into emp values(7499,'陈二','推销员',7698,'1981-02-20',1600,300,30);
insert into emp values(7521,'张三','推销员',7698,'1981-02-22',1250,500,30);
insert into emp values(7566,'李四','经理',7839,'1981-04-02',2975,null,20);
insert into emp values(7654,'王五','推销员',7698,'1981-09-28',1250,1400,30);
insert into emp values(7698,'赵六','经理',7839,'1981-05-01',2850,null,30);
insert into emp values(7782,'孙七','经理',7839,'1981-06-09',2450,null,10);
insert into emp values(7788,'周八','分析师',7566,'1987-06-13',3000,null,20);
insert into emp values(7839,'吴九','总裁',null,'1981-11-17',5000,null,10);
insert into emp values(7844,'郑十','推销员',7698,'1981-09-08',1500,0,30);
insert into emp values(7876,'郭靖','职员',7788,'1987-06-13',1100,null,20);
insert into emp values(7900,'令狐冲','职员',7698,'1981-12-03',950,null,30);
insert into emp values(7902,'张无忌','分析师',7566,'1981-12-03',3000,null,20);
insert into emp values(7934,'杨过','职员',7782,'1983-01-23',1300,null,10);

-- 1．列出至少有一个员工的所有部门。

-- 2．列出薪金比"刘一"多的所有员工。

-- 3．***** 列出所有员工的姓名及其直接上级的姓名。

-- 4．列出受雇日期早于其直接上级的所有员工。

-- 5．列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门。

-- 6．列出所有job为“职员”的姓名及其部门名称。

-- 7．列出最低薪金大于1500的各种工作。

-- 8．列出在部门 "销售部" 工作的员工的姓名，假定不知道销售部的部门编号。

-- 9．列出薪金高于公司平均薪金的所有员工。

-- 10．列出与"周八"从事相同工作的所有员工。

-- 11．列出薪金等于部门30中员工的薪金的所有员工的姓名和薪金。

-- 12．列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金。

-- 13．列出在每个部门工作的员工数量、平均工资。

-- 14．列出所有员工的姓名、部门名称和工资。

-- 15．列出所有部门的详细信息和部门人数。

-- 16．列出各种工作的最低工资。

-- 17．列出各个部门的 经理 的最低薪金。

-- 18．列出所有员工的年工资,按年薪从低到高排序。

-- 19.查出emp表中薪水在3000以上（包括3000）的所有员工的员工号、姓名、薪水。

-- 20.查询出所有薪水在'陈二'之上的所有人员信息。

-- 21.查询出emp表中部门编号为20，薪水在2000以上（不包括2000）的所有员工，显示他们的员工号，姓名以及薪水，以如下列名显示：员工编号 员工名字 薪水

-- 22.查询出emp表中所有的工作种类（无重复）

-- 23.查询出所有奖金（comm）字段不为空的人员的所有信息。

-- 24.查询出薪水在800到2500之间（闭区间）所有员工的信息。（注：使用两种方式实现and以及between and）

-- 25.查询出员工号为7521，7900，7782的所有员工的信息。（注：使用两种方式实现，or以及in）

-- 26.查询出名字中有“张”字符，并且薪水在1000以上（不包括1000）的所有员工信息。

-- 27.查询出名字第三个汉字是“忌”的所有员工信息。

-- 28.将所有员工按薪水升序排序，薪水相同的按照入职时间降序排序。

-- 29.将所有员工按照名字首字母升序排序，首字母相同的按照薪水降序排序。 order by convert(name using gbk) asc;

-- 30.查询出最早工作的那个人的名字、入职时间和薪水。

-- 31.显示所有员工的名字、薪水、奖金，如果没有奖金，暂时显示100.

-- 32.显示出薪水最高人的职位。

-- 33.查出emp表中所有部门的最高薪水和最低薪水，部门编号为10的部门不显示。

-- 34.删除10号部门薪水最高的员工。

-- 35.将薪水最高的员工的薪水降30%。

-- 36.查询员工姓名，工资和 工资级别(工资>=3000 为3级，工资>2000 为2级，工资<=2000 为1级)
-- 语法：case when ... then ... when ... then ... else ... end

-- 34.删除10号部门薪水最高的员工。
# 分析：
-- 1 找到10号部门的最高薪水
-- 2 找到10号部门的最高薪水 对应人的编号
-- 3 根据编号删除员工

select max(sal)
from emp where empno =10;

select empno
from emp where deptno = 10 and sal = (select max(sal)
                                      from emp where empno =10);

delete
from emp
where empno = (select empno
               from emp
               where deptno = 10
                 and sal = (select max(sal)
                            from emp
                            where empno = 10));

