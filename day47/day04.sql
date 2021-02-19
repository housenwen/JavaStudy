-- 器材号表
CREATE TABLE `equipment`
(
    `equip_id`   int(11) NOT NULL AUTO_INCREMENT,
    `equip_name` varchar(30) DEFAULT NULL,
    PRIMARY KEY (`equip_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
-- 采购表
CREATE TABLE `equip_purchase`
(
    `id`             int(11)     NOT NULL AUTO_INCREMENT,
    `equip_id`       int(11) DEFAULT NULL,
    `amount`         int(11)     NOT NULL,
    `supplier`       varchar(30) NOT NULL,
    `unit_price`     double      NOT NULL,
    `total_price`    double      NOT NULL,
    `estimated_date` date    DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_equip_purchase` (`equip_id`),
    CONSTRAINT `FK_equip_purchase` FOREIGN KEY (`equip_id`) REFERENCES `equipment` (`equip_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
-- 入库表
CREATE TABLE `equip_in`
(
    `id`         int(11)     NOT NULL AUTO_INCREMENT,
    `equip_id`   int(11)     DEFAULT NULL,
    `time_in`    date        NOT NULL,
    `supplier`   varchar(30) NOT NULL,
    `telephone`  varchar(30) DEFAULT NULL,
    `amount`     int(11)     NOT NULL,
    `unit_price` double      DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_equip_in` (`equip_id`),
    CONSTRAINT `FK_equip_in` FOREIGN KEY (`equip_id`) REFERENCES `equipment` (`equip_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
-- 出库表
CREATE TABLE `equip_out`
(
    `id`                int(11)     NOT NULL AUTO_INCREMENT,
    `equip_id`          int(11) DEFAULT NULL,
    `time_out`          date        NOT NULL,
    `amount`            int(11)     NOT NULL,
    `responsible_man`   varchar(30) NOT NULL,
    `department_borrow` varchar(30) NOT NULL,
    `borrow_man`        varchar(30) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_equip_out` (`equip_id`),
    CONSTRAINT `FK_equip_out` FOREIGN KEY (`equip_id`) REFERENCES `equipment` (`equip_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;
-- 库存表
CREATE TABLE `inventory`
(
    `id`         int(11) NOT NULL,
    `curr_store` int(11) NOT NULL,
    `max_store`  int(11) DEFAULT NULL,
    `min_store`  int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `inv_equip_fk` FOREIGN KEY (`id`) REFERENCES `equipment` (`equip_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


#todo  插入测试数据
# 器材表
insert into `equipment`(`equip_id`, `equip_name`)
values (1, '投影仪'),
       (2, '智能柜员机'),
       (3, '打印机'),
       (4, '话务机');

#todo 入库
insert into `equip_in`(`id`, `equip_id`, `time_in`, `supplier`, `telephone`, `amount`, `unit_price`)
values (1, 1, '2020-05-05', 'TOSHIBA', '18888888888', 5, 1588),
       (2, 1, '2020-08-08', 'HP', '13333333333', 10, 2688),
       (3, 2, '2020-02-02', 'Unknown', '12222222222', 50, 4888);

#todo 出库
insert into `equip_out`(`id`, `equip_id`, `time_out`, `amount`, `responsible_man`, `department_borrow`, `borrow_man`)
values (1, 1, '2020-06-06', 1, '小绿', '财务部', '小明'),
       (2, 1, '2020-06-07', 3, '小绿', '运营部', '小红'),
       (3, 2, '2020-08-05', 4, '小绿', '大堂', '小黑'),
       (4, 2, '2020-04-03', 2, '小黄', '财务部', '小明');

#todo 订单
insert into `equip_purchase`(`id`, `equip_id`, `amount`, `supplier`, `unit_price`, `total_price`, `estimated_date`)
values (1, 1, 10, 'HP', 2999, 29990, '2020-05-23'),
       (2, 1, 10, 'TOSHIBA', 1888, 18880, '2020-12-12'),
       (3, 2, 50, 'Unknown', 5000, 250000, '2020-02-02');

#todo 库存
insert into `inventory`(`id`, `curr_store`, `max_store`, `min_store`)

values (1, 50, 100, 20),
       (2, 100, 1000, 80);

# todo 订单器材表
select *
from equipment
         inner join equip_purchase ep on
    equipment.equip_id = ep.equip_id;



#todo 业务需求：
#
#todo 基本功能：
# 1)查询2020年采购过的器材对应的采购总金额和采购次数；
#  		提示：查询数据包括器材编号，器材名称，采购总金额和采购次数

select equipment.equip_id,
       equip_name,
       sum(equip_purchase.total_price) as yearMoney,
       count(equip_purchase.id)        as acount
from equipment,
     equip_purchase
where equipment.equip_id = equip_purchase.equip_id
  and year(equip_purchase.estimated_date) = '2020'
group by equipment.equip_id;

select equipment.equip_id,
       equip_name,
       sum(ep.total_price) as yearMoney,
       count(ep.id)        as acount
from equipment
         inner join equip_purchase ep on
        equipment.equip_id = ep.equip_id
        and year(ep.estimated_date) = '2020'
group by equipment.equip_id;



#todo 2）查询2020年各个器材的采购总金额和采购次数，如果器材没有被采购过则采购次数和采购金额显示为0；
# 	提示：
# 			1.left join关联查询 +ifnull函数使用；
# 			2.查询数据包括器材编号，器材名称，采购总金额和采购次数

select equipment.equip_id,
       equip_name,
       ifnull(sum(ep.total_price), 0) as yearMoney,
       ifnull(count(ep.id), 0)        as acount
from equipment
         left join equip_purchase ep
                   on equipment.equip_id = ep.equip_id
                       and year(ep.estimated_date) = '2020'
group by equipment.equip_id;



#todo 3）查看2020年出库数量最多的商品的前2名；
#      提示： 查询结果包含商品信息和出库数量


select equip_id, sum(amount) as num
from equip_out
where year(time_out) = '2000'
group by equip_id
limit 2;


select equipment.*, eo.num
from equipment
         inner join (select equip_id, sum(amount) as num
                     from equip_out
                     where year(time_out) = '2000'
                     group by equip_id
                     limit 2) eo on equipment.equip_id = eo.equip_id;


-- todo 2.将上述结果作为一张表与器材表关联查询
SELECT equipment.*,
       tmp.num
FROM equipment,
     (
         SELECT equip_out.equip_id,
                SUM(equip_out.amount) AS num
         FROM equip_out
         WHERE YEAR(equip_out.time_out) = '2020'
         GROUP BY equip_out.equip_id
         LIMIT 2
     ) AS tmp
WHERE equipment.equip_id = tmp.equip_id;


#todo 4)查询达到库存预警的器材对应的采购流水，查询的数据包含器材名称和采购流水信息
# 	说明：（当前库存量—最小库存量） < 25时，触发库存告警，需要加急采购；

select e.equip_name, ep.*
from inventory
         inner join equipment e on inventory.id = e.equip_id
         inner join equip_purchase ep on e.equip_id = ep.equip_id
    and (inventory.curr_store - inventory.min_store) < 25;

select *
from inventory
         inner join equipment e on inventory.id = e.equip_id
         inner join equip_purchase ep on e.equip_id = ep.equip_id;



#todo 扩展：
#
#todo 1）查询今年各个采购器材名称，采购金额，采购次数，以及出库数量的统计；
#      要求:显示所有器材名称，如果对应采购金额，采购次数和出库数量为NULL，则显示为0；


select equipment.equip_id,
       equip_name,
       ifnull(ep.time, 0),
       ifnull(eo.num, 0),
       ifnull(ep.purmoney, 0)
from equipment
         left join (select equip_id, sum(total_price) as purmoney, SUM(amount) as time
                    from equip_purchase
                    group by equip_id) ep on equipment.equip_id = ep.equip_id

         left join (select equip_id, sum(amount) as num
                    from equip_out
                    GROUP BY equip_id) eo on equipment.equip_id = eo.equip_id
group by equipment.equip_id;



SELECT equipment.equip_id,
       equipment.equip_name,
       ifnull(purchase.purmoney, 0) AS purmoney,
       ifnull(purchase.time, 0)     AS time,
       ifnull(eout.num, 0)          AS num
FROM equipment
         LEFT JOIN (
    SELECT equip_id,
           sum(total_price) AS purmoney,
           count(*)         AS time
    FROM equip_purchase
    WHERE YEAR(estimated_date) = YEAR(
            now())
    GROUP BY equip_id
) AS purchase ON equipment.equip_id = purchase.equip_id
         LEFT JOIN (SELECT equip_id, sum(amount) AS num
                    FROM equip_out
                    WHERE YEAR(time_out) = YEAR(now())
                    GROUP BY equip_id) AS eout ON equipment.equip_id = eout.equip_id;



#todo 2）创建存储过程，完成器材出库功能；
# 说明：
# 	 1）逻辑1：查看出库数据量与库存数量的比对判断
#      2）满足条件则更改库存表，并插入出库流水（在一个事务中完成）
#      3）存储过程返回出库是否成功的表示；
#      4）存储过程入参与出参自行定义，功能完成即可！

delimiter $ -- 定义开始标识
create procedure inventoryE(in eid int, in outtime date,
                           in num int, in dep varchar(30), in rman varchar(30),
                           in bman varchar(30), out flag int)
begin
    -- 声明
    declare otherNum int default 0;
    declare minNum int;
    -- 声明出库影响的行数
    declare outLine int default 0;
    -- 声明扣减粗存影响的行数
    declare subLine int default 0;
    -- 开启事务
    start transaction ;
    -- 排它读 ， 防止其他事务并发修改，出现并发问题
    select curr_store as num, min_store
    into otherNum,minNum
    from inventory
    where id = eid for
    update;
    -- 思路：乐观锁 inventory添加一个字段 version 0---》

    -- 1.select version from inventory where id=xx;--->1
    -- 2.update inventory set xxxx,version=version+1 where id=xxx and version=1;

    if otherNum > minNum then
        -- 1.扣减库存
        update inventory set curr_store = otherNum where id = eid;
        select row_count() into subLine;
        -- 2.生成出库流水
        insert into equip_out values (null, eid, outtime, num, rman, dep, bman);
        select row_count() into outLine;

        if subLine > 0 and outLine > 0 then
            -- 事务提交
            commit;
            -- 设置出库成功
            set flag = 1;
            select '出库成功！';
        else
            rollback;
            set flag = 0;
        end if;

    else
        set flag = 0;
        rollback;
        select '出库失败!';

    end if;
end $

call inventoryE(2,'2020-12-12',10,'depxx','rman','bman',@flag);
select @flag;

call inventoryE(2,'2020-12-12',20,'depxx','rman','bman',@flag);
select @flag;


delimiter $
create procedure inventoryE(in eid int,in outtime date,
                            in num int,in dep varchar(30),in rman varchar(30),
                            in bman varchar(30),out flag int)
begin
    declare otherNum int default 0;
    declare minNum int;
    -- 声明出库影响的行数
    declare outLine int default 0;
    -- 声明扣减库存影响的行数
    declare subLine int default 0;
    -- 开启事务
    start transaction;
    -- 排它读，防止其它事务并发修改，出现并发问题
    select curr_store-num, min_store into otherNum,minNum  from inventory where id=eid for UPDATE;
    -- 思路：乐观锁 inventory添加一个字段 version 0---》

    -- 1.select version from inventory where id=xx;--->1
    -- 2.update inventory set xxxx,version=version+1 where id=xxx and version=1;
    if otherNum > minNum then
        -- 1.扣减库存
        update inventory set curr_store=otherNum where id=eid;
        select row_count() into subLine;
        -- 2.生成出库流水
        insert into equip_out values(null,eid,outtime,num,rman,dep,bman);
        select row_count() into outLine;
        if subLine >0 and outLine >0 then
            -- 事务提交
            commit;
            -- 设置出库成功
            set flag=1;
        else
            rollback;
            set flag=0;
        end if;
    else
        set flag=0;
        rollback;
        select '出库失败!';
    end if;
end $

INSERT  INTO `inventory`(`id`,`curr_store`,`max_store`,`min_store`) VALUES (1,50,100,20),(2,100,1000,80);
update inventory set curr_store = 100 where id = 1;
update inventory set curr_store = 1000 where id = 2;
call inventoryE(2,'2020-12-12',10,'depxx','rman','bman',@flag);
select @flag;

call inventoryE(2,'2020-12-12',20,'depxx','rman','bman',@flag);
select @flag;


delimiter $
create procedure inventoryX(in eid int, in outtime date, in num int, in dep varchar(30),
                            in rman varchar(30), in bman varchar(30), out flag int)
begin
    declare otherNum int default 0;
    declare minNum int;
    declare outLine int default 0;
    declare subLine int default 0;

    start transaction ;
    select curr_store as num, min_store
    into otherNum,minNum
    from inventory
    where id = eid for
    update;
    if otherNum > minNum then
        update inventory set curr_store = otherNum where id = eid;
        select row_count() into subLine;
        insert into equip_out values (null, eid, outtime, num, rman, dep, bman);
        select row_count() into outLine;
        if subLine > 0 and outLine > 0 then
            select '成功提交！';
            commit;
            set flag = 1;
        else
            rollback;
            set flag = 0;
        end if;
    else
        set flag = 0;
        rollback;
        select '出库失败！';
    end if;
end$

call inventoryX(2, '2020-12-12', 10, 'depxx', 'rman', 'bman', @flag);
select @flag;

