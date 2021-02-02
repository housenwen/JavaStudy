

#todo 4.触发器测试数据
create table emp(
    id int primary key auto_increment,
    name varchar(30) not null ,
    age int ,
    salary int
);

insert into emp values (null,'金毛狮王',40,2500);
insert into emp values (null,'蝙蝠王',38,3100);

-- todo 创建一张日志表,存放日志信息

create table emp_logs(
    id int primary key auto_increment,
    operation varchar(20) not null comment '操作类型,insert/update/delete',
    operate_time datetime not null comment '操作时间',
    operate_id int not null comment '操作表的id,emp表数据的id',
    operate_params varchar(500) comment '操作参数,插入emp中的数据'
)engine = innodb default charset =utf8;

