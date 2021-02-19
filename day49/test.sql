CREATE TABLE country
(
    country_id   int(11) PRIMARY KEY AUTO_INCREMENT,
    country_name varchar(100) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE city
(
    city_id    int(11) PRIMARY KEY AUTO_INCREMENT,
    city_name  varchar(50) NOT NULL,
    country_id int(11)     NOT NULL,
    constraint ref_country_fk foreign key (country_id) references country (country_id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
-- 测试数据
insert into country
values (1, 'China');
insert into country
values (2, 'America');
insert into country
values (3, 'Japan');
insert into country
values (4, 'UK');
insert into city
values (1, '西安', 1);
insert into city
values (2, 'NewYork', 2);
insert into city
values (3, '北京', 1);
insert into city
values (4, '上海', 1);

# todo 创建名称为pro_test1存储过程,完成查询country表中的行记录数的功能

delimiter $
-- 2.创建存储过程
create procedure  pro_teset1()
begin   -- 在begin和end之间写sql逻辑
select count(*) from country;
end$

call pro_teset1();

#todo 方式1:通过mysql.proc数据库根据db字段查看
# select * from mysql.proc where db='day04';
# 方式2:查看创建某个存储过程的语句信息
# show create procedure 存储过程的名称;
# ③删除存储过程
# drop procedure 存储过程名;


