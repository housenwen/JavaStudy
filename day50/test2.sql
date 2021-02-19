
create table emp(
    id int primary key auto_increment,
    name varchar(20),
    age int,
    birth date,
    sex varchar(10),
    meno varchar(20)

);

insert into emp values (null,'徐洪国',37,'1979-03-23','男','高中');
insert into emp values (null,'王芳',26,'1988-02-06','女','本科');
insert into emp values (null,'李达康',24,'1990-04-02','男','硕士');
insert into emp values (null,'侯亮平',30,'1984-09-12','女','博士');
insert into emp values (null,'徐夫子',27,'1987-12-30','男','大专');

#todo
# 1.请编写sql语句对年龄进行升序排列
# 2.请编写sql语句查询对“徐”姓开头的人员名单
# 3.请编写sql语句修改“李达康”的年龄为“45”
# 4.请编写sql删除王芳这表数据记录。

select *
from emp order by age asc ;

select *
from emp where name like '徐%';

select *
from emp where name = '李达康';

update emp set age = 45 where name = '李达康';





















