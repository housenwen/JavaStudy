#todo -- 1. 准备表 千万数据生成SQL脚本

create table user
(
    id       int,
    username varchar(32),
    password varchar(32),
    sex      varchar(6),
    email    varchar(69)
);

-- 2. 创建存储过程，实现批量插入记录
delimiter $$ -- 声明存储过程的结束符号为$$
-- 可以将下面的存储过程理解为java中的一个方法，插入千万条数据之后，在调用存储过程
create procedure auto_insert()

begin
    declare i int default 1;
    start transaction ;-- 开启事务
    while (i <= 1000)
        do
            insert into user
            values (i, concat('jack', i), md5(i), 'male', concat('jack', i, '@itcast.cn'));
            set i = i + 1;
        end while;
    commit;-- 提交
end $$-- 声明结束

delimiter ; -- 重新声明分号为结束符号

-- 3. 查看存储过程

show create procedure auto_insert;

-- 4. 调用存储过程

call auto_insert();





