
create table teacher(
    id int not null primary key auto_increment,
    name varchar(10) not null unique
);

create table student(
    id int not null primary key auto_increment,
    name varchar(20) not null unique ,
    city varchar(78) not null ,
    age int
);

create table course(
    id int not null primary key auto_increment,
    name varchar(20) not null unique ,
    teacher_id int not null ,
    foreign key (teacher_id) references teacher(id)
);

create table studentCourse(
    student_id int not null ,
    course_id int not null ,
    score double not null ,
    foreign key (student_id) references student(id),
    foreign key (course_id) references course(id)
);

insert into teacher values (null,'关羽');
insert into teacher values(null,'张飞');
insert into teacher values(null,'赵云');

insert into student values(null,'小王','北京',20);
insert into student values(null,'小李','上海',18);
insert into student values(null,'小周','北京',22);
insert into student values(null,'小刘','北京',21);
insert into student values(null,'小张','上海',22);
insert into student values(null,'小赵','北京',17);
insert into student values(null,'小蒋','上海',23);
insert into student values(null,'小韩','北京',25);
insert into student values(null,'小魏','上海',18);
insert into student values(null,'小明','广州',20);

insert into course values(null,'语文',1);
insert into course values(null,'数学',1);
insert into course values(null,'生物',2);
insert into course values(null,'化学',2);
insert into course values(null,'物理',2);
insert into course values(null,'英语',3);

insert into studentcourse values(1,1,80);
insert into studentcourse values(1,2,90);
insert into studentcourse values(1,3,85);
insert into studentcourse values(1,4,78);
insert into studentcourse values(2,2,53);
insert into studentcourse values(2,3,77);
insert into studentcourse values(2,5,80);
insert into studentcourse values(3,1,71);
insert into studentcourse values(3,2,70);
insert into studentcourse values(3,4,80);
insert into studentcourse values(3,5,65);
insert into studentcourse values(3,6,75);
insert into studentcourse values(4,2,90);
insert into studentcourse values(4,3,80);
insert into studentcourse values(4,4,70);
insert into studentcourse values(4,6,95);
insert into studentcourse values(5,1,60);
insert into studentcourse values(5,2,70);
insert into studentcourse values(5,5,80);
insert into studentcourse values(5,6,69);
insert into studentcourse values(6,1,76);
insert into studentcourse values(6,2,88);
insert into studentcourse values(6,3,87);
insert into studentcourse values(7,4,80);
insert into studentcourse values(8,2,71);
insert into studentcourse values(8,3,58);
insert into studentcourse values(8,5,68);
insert into studentcourse values(9,2,88);
insert into studentcourse values(10,1,77);
insert into studentcourse values(10,2,76);
insert into studentcourse values(10,3,80);
insert into studentcourse values(10,4,85);
insert into studentcourse values(10,5,83);







#todo -- 1、查询没学过关羽老师课的同学的学号、姓名。
# 具体步骤如下所示：
# 1）、在老师表中查询关羽老师的工号id;
# 2）、在课程表中通过关羽老师工号id查询关羽老师教授的课程的编号id;
# 3）、在中间表中通过关羽老师教授的课程的编号id查询学过这些课程的学生编号id;
# 4）、在学生表中通过学生编号id查询到学生的学号和姓名，
# 但是注意，这里要求是查询没有学习过关羽老师上课的学生，所以我们在这里需要使用 not in(.....);

select id
from teacher
where name = '关羽';

select id
from course
where teacher_id = (select id
            from teacher
            where name = '关羽');

select sc.student_id
from studentCourse sc where sc.course_id in (select id
                                             from course
                                             where teacher_id = (select id
                                                                 from teacher
                                                                 where name = '关羽'));

select id,name
from student
where id not in(select sc.student_id
                from studentCourse sc where sc.course_id in (select id
                                                             from course
                                                             where teacher_id = (select id
                                                                                 from teacher
                                                                                 where name = '关羽'))
);

#todo -- 2、查询每个没有学三门课以上的同学的学号、姓名。
# 分析：
# 1）、在中间表中查询每个学生学习的课程数量，根据学号进行分组，
# 添加过滤条件：每个学生选择的课程数量 <=3;
# 2）、最后通过学生的学号到student表中查询姓名;

select s.id, s.name
from (select student_id
      from studentCourse
      group by student_id
      having count(*) <= 3) as sc
         inner join student s on sc.student_id = s.id;

select student_id, count(*)
from studentCourse
group by student_id
having count(*) <= 3;

select *
from student
where id in (select student_id
             from studentCourse
             group by student_id
             having count(*) <= 3);


#todo -- 3、查询每个学科成绩最高和最低的分。
# 分析：
# 1）、在中间表中查询每个学科的最高分和最低分，由于是每个学科，所以按照学科编号进行分组;
# 2）、显示的时候，显示每一个课程的最高分和最低分，以及课程的id;
# 3）、通过上述查询的结果作为临时表和课程表course关联，
#     按照课程的id查询课程的名称，显示最高分和最低分;


select course_id,max(score),min(score)
from studentCourse as sc group by course_id;

select name,max,min
from course
         inner join (select course_id, max(score) as max, min(score) as min
                     from studentCourse
                     group by course_id) sC on course.id = sC.course_id;


#todo -- 4、查询每个学生信息和平均成绩。
# 分析：
# 1）、在中间表查询每个学生的平均成绩和学号，由于对每个学生进行查询，所以需要对学生进行分组;
# 2）、将上述查询的结果作为临时表和学生表进行关联查询，查询学生信息和平均成绩;

select student_id,avg(score)
from studentCourse group by student_id;

select student.*,avg
from student
         inner join (select student_id, avg(score) as avg
                     from studentCourse
                     group by student_id) sC on student.id = sC.student_id;


#todo --5、查询上海和北京学生数量。
# 分析：
# 由于查询上海和北京的学生数量，所以这里我们只需要查询一个表student即可。
# 我们可以假设查询每个城市的学生数量，所以可以按照城市进行分组。然后增加条件是北京和上海。

select city,count(*)
from student group by city ;

select city,count(*)
from student group by city
having city in ('上海','北京');


#todo -- 6、查询不及格的学生信息和课程信息。
# 分析：学生信息在student表中，课程信息在course表中。学生表和课程表属于多对多关系，引入中间表studentcourse。
# 并且分数在中间表中。
# 1）、在中间表中查询不及格的学生的id和课程的id;
# 2）、通过课程id和学生id以及上述查询内容进行联合查询;

select course_id,student_id,score
from studentCourse where score<60 group by course_id;

select student.*,c.*,sc.score
from student
         inner join (select course_id,student_id,score
                     from studentCourse where score<60 group by course_id) sC on student.id = sC.student_id
         inner join course c on sC.course_id = c.id;


#todo -- 7、统计每门课程的学生选修人数（超过四人的进行统计）。
# 分析：
# 1）、在中间表中对课程进行分组查询，添加过滤条件每门课程的人数>4;
# 2）、显示课程名称，和学生的选修人数;

select course_id,count(*)
from studentCourse group by course_id
having count(*)>4;

select *
from course
         inner join (select course_id, count(*)
                     from studentCourse
                     group by course_id
                     having count(*) > 4) sC
                    on course.id = sC.course_id;

select name,sc.count
from course
         inner join (select course_id, count(*) as count
                     from studentCourse
                     group by course_id
                     having count(*) > 4) sC
                    on course.id = sC.course_id;


#todo --8、查询每个平均成绩大于70分的同学的学号和平均成绩。
# 分析：
# 1）在中间表中查询每个学生的平均成绩，遇到每个了，那么得分组，按照学号分组，先按学号进行分组;
# 2）分组后添加条件成绩>70分;

select student_id,avg(score)
from studentCourse group by student_id;

select student_id,avg(score)
from studentCourse group by student_id
having avg(score)>70;

#todo -- 9、查询每个同学的学号、姓名、选课数、总成绩。
# 分析：
# 1）、在中间表中查询每个学生的选课数和总成绩，遇到每个，分组，按照学生学号进行分组;
# 2）、由于还得显示学号和姓名，并且姓名在student表中，所以我们将上述结果作为临时表和学生表关联。
# 目的是查找临时表和student表中学号相等时查找学号，姓名，选课数，总成绩。

select student_id,count(*),sum(score)
from studentCourse group by student_id;

select *
from student
         inner join (select student_id, count(*), sum(score)
                     from studentCourse
                     group by student_id) sC on student.id = sC.student_id;

select id,name,count,sum
from student
         inner join (select student_id, count(*) count, sum(score) sum
                     from studentCourse
                     group by student_id) sC on student.id = sC.student_id;

select student.id,student.name,temp.cou,temp.sumScore
from student,(select student_id,count(*) as cou,sum(score) as sumScore
              from studentcourse
              group by student_id) as temp
where student.id=temp.student_id;

#todo --10、查询学过赵云老师所教课的同学的学号、姓名。
# 1）、在老师表中查询赵云老师的工号id;
# 2）、在课程表中通过赵云老师工号id查询赵云老师教授的课程的编号id;
# 3）、在中间表中通过赵云老师教授的课程的编号id查询学过这些课程的学生编号id;
# 4）、在学生表中通过学生编号id查询到学生的学号和姓名;

select id
from teacher where name = '赵云';

select id
from course where teacher_id = (select id
                                from teacher where name = '赵云');

select student_id
from studentCourse where course_id in (select id
                                      from course where teacher_id = (select id
                                                                      from teacher where name = '赵云'));

select id, name
from student
where id in (select student_id
             from studentCourse
             where course_id in (select id
                                 from course
                                 where teacher_id = (select id
                                                     from teacher
                                                     where name = '赵云'))
);





