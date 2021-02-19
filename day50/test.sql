CREATE TABLE `student_course`
(
    `name`   varchar(10) DEFAULT NULL,
    `course` varchar(10) DEFAULT NULL,
    `grade`  int(11)     DEFAULT NULL,
    UNIQUE KEY `NewIndex1` (`name`, `course`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `student_course` */

insert into `student_course`(`name`, `course`, `grade`)
values ('张三', '语文', 81),
       ('张三', '数学', 50),
       ('李四', '语文', 76),
       ('李四', '数学', 90),
       ('王五', '语文', 81),
       ('王五', '数学', 100),
       ('王五', '英语', 90);

# 1.统计每个学生考试科目的数量

select name, count(course) as 考试科目数量
from student_course
group by name;


# 2.统计考试不及格的学生有哪些？

select name, course
from student_course
where grade < 60;

select name
from student_course
group by name
having min(grade) < 60;


# 3. 用一条SQL 语句 查询出每门课都大于80 分的学生姓名

select name
from student_course
where grade > 80
group by course;

select name
from student_course
group by name
having min(grade) > 80;

