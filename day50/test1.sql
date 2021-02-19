#todo 1.表结构
# - 学生表student(id,name)
# - 课程表course(id,name)
# - 学生课程表student_course(sid,cid,score)
# 2.创建表的SQL
create table student
(
    id   int unsigned primary key auto_increment,
    name char(10) not null
);
insert into student(name)
values ('张三'),
       ('李四');

create table course
(
    id   int unsigned primary key auto_increment,
    name char(20) not null
);
insert into course(name)
values ('语文'),
       ('数学');

create table student_course
(
    sid   int unsigned,
    cid   int unsigned,
    score int unsigned not null,
    foreign key (sid) references student (id),
    foreign key (cid) references course (id),
    primary key (sid, cid)
);
insert into student_course
values (1, 1, 80),
       (1, 2, 90),
       (2, 1, 90),
       (2, 2, 70);

# 问题1.查询student表中重名的学生，结果包含id和name，按name,id升序
select distinct id,name
from student  order by id asc ;

# 2.在student_course表中查询平均分不及格的学生，列出学生id和平均分
select sid,avg(score) as 平均分
from student_course group by sid
having avg(score);

# 3.在student_course表中查询每门课成绩都不低于80的学生id
select sid
from student_course group by sid
having  min(score) >=80;

# 4.查询每个学生的总成绩，结果列出学生姓名和总成绩 如果使用下面的sql会过滤掉没有成绩的人
select name,sum(score) as 总成绩
from student inner join student_course sc on student.id = sc.sid
group by id;
# 5.总成绩最高的学生，结果列出学生id和总成绩 下面的sql效率很低，因为要重复计算所有的总成绩。

select name
from student inner join student_course sc on student.id = sc.sid
group by id
having sum(score);

# 6.在student_course表查询课程1成绩第2高的学生，如果第2高的不止一个则列出所有的学生

select *
from student_course group by cid = 1
having score;


# 7.在student_course表查询各科成绩最高的学生，结果列出学生id、课程id和对应的成绩
select sid,cid,score
from student_course group by cid, sid, score;

# 8.在student_course表中查询每门课的前2名，结果按课程id升序，同一课程按成绩降序 这个问题也 就是取每组的前N条纪录

select *
from student_course group by cid
having sum(score)
order by cid
;