-- todo 用户表
create table tb_user(
    user_id int primary key auto_increment,
    name varchar(30) not null ,
    telephone varchar(20),
    address varchar(30),
    postcode varchar(10)
);

-- todo 商品表
create table tb_good(
    good_id int primary key auto_increment,
    name varchar(30) not null ,
    description varchar(100),
    expired_time date,
    price double
);

# todo 订单表
create table tb_order(
    order_id int primary key auto_increment,
    user_id int ,
    order_time date,
    order_type varchar(30),
    order_status int,
    constraint fk_order_user foreign key (user_id) references tb_user(user_id)
);

# todo 订单明细表
create table tb_order_good(
    order_id int,
    good_id int,
    good_amount int not null ,
    good_price double not null ,
    constraint fk_tb_order foreign key (order_id) references tb_order(order_id),
    constraint fk_tb_good foreign key (good_id) references tb_good(good_id)
);


-- 用户测试数据
INSERT INTO `tb_user` VALUES (1, '张三', '18811004789', '上海浦东新区', '000000');
INSERT INTO `tb_user` VALUES (2, '李四', '18777664529', '北京海淀区', '788787');
INSERT INTO `tb_user` VALUES (3, '王五', '13467890124', '北京朝阳区', '898989');
-- 商品信息
INSERT INTO `tb_good` VALUES (1, '苹果12', '一级棒', '2020-12-30',1000);
INSERT INTO `tb_good` VALUES (2, 'iphone11', '真贵', '2020-11-25',2500);
INSERT INTO `tb_good` VALUES (3, '华为meta40', '好好', '2020-11-27',1000);
-- 订单信息
INSERT INTO `tb_order` VALUES (1, 1, '2020-11-10', '1', 1);
INSERT INTO `tb_order` VALUES (2, 1, '2020-11-12', '1', 1);
INSERT INTO `tb_order` VALUES (3, 2, '2020-11-11', '1', 1);
INSERT INTO `tb_order` VALUES (4, 3, '2020-11-08', '1', 1);
-- 订单明细
INSERT INTO `tb_order_good` VALUES (1, 2, 2, 5000);
INSERT INTO `tb_order_good` VALUES (2, 2, 1, 1000);
INSERT INTO `tb_order_good` VALUES (1, 3, 3, 3000);
INSERT INTO `tb_order_good` VALUES (2, 3, 2, 2000);
INSERT INTO `tb_order_good` VALUES (3, 3, 1, 1000);
INSERT INTO `tb_order_good` VALUES (4, 3, 4, 4000);

#todo 业务要求:
# 1）使用事务完成ID=2用户于'2020-12-25'购买5件goods_id=3的商品完成提交订单功能；
# 		步骤1:插入订单ID=10的订单记录;
# 步骤2:插入订单明细信息;
# 上述操作在同一事务中完成;
# 开启事务
start transaction ;
insert into tb_order values (10,2,'2020-12-25',1,1);
insert into tb_order_good values (10,3,5,2500);
commit ;

#todo 2)查询ID=1的用户2020年的消费金额,包含用户信息;
# 提示:用户的消费额为消费明细表中good_amount*good_price;

select tu.*, sum(tog.good_amount * tog.good_price) as expenditure
from tb_user as tu,
     tb_order as td,
     tb_order_good as tog
where tu.user_id = td.user_id
  and td.order_id = tog.order_id
  and td.order_status = 1
  and tu.user_id = 1;

#todo 3）统计每种商品卖出的总量,包含商品名称和数量；
# 	 提示:1.商品卖出的数量就是有效订单对应的商品数量
# 	     2.订单支付完成(status=1),代表商品有效卖出;

select tb_good.name,count(tog.good_amount) as num
from tb_order,
     tb_order_good as tog,
     tb_good
where tb_order.order_id = tog.order_id
and tog.good_id = tb_good.good_id
and tb_order.order_status = 1
group by tog.good_id;

#todo  4）统计消费总额大于15000的用户有哪些
# 	 提示: 1)根据用户ID分组,分组之后过滤,使用having ;
# 2)只显示用户信息;

select *
from tb_user,
     tb_order,
     tb_order_good as tog
where tb_user.user_id = tb_order.user_id
  and tb_order.order_id = tog.order_id
group by tb_user.user_id
having sum(tog.good_amount*tog.good_price)>15000;

#todo 5）将4题中功能封装到视图tmp下

create view tem as
select tb_user.*
from tb_user,
     tb_order,
     tb_order_good as tog
where tb_user.user_id = tb_order.user_id
  and tb_order.order_id = tog.order_id
group by tb_user.user_id
having sum(tog.good_amount*tog.good_price)>15000;


create view test as
select distinct tb_user.*
from tb_user inner join tb_order t on tb_user.user_id = t.user_id
where tb_user.user_id >= 2;


#todo 4）使用视图完成每种商品的出货量统计功能；
# -- 使用视图完成每种商品的出货量统计功能；
create view goods_out_view as
select tb_good.name,
       sum(tog.good_amount) as num
from tb_order,
     tb_order_good as tog,
     tb_good
where tb_order.order_id = tog.order_id
  and tog.good_id = tb_good.good_id
  and tb_order.order_status = 1
group by tb_good.name
order by num desc;

select *
from goods_out_view;

#todo 5）视图统计消费总额大于1W的用户有哪些
create view consumerMoney as
select tb_user.*, sum(tog.good_amount * tog.good_price) as totalMoney
from tb_user,
     tb_order,
     tb_order_good as tog
where tb_user.user_id = tb_order.user_id
  and tb_order.order_id = tog.order_id
  and tb_order.order_status = 1
group by tb_user.user_id
having totalMoney > 10000;

select *
from consumerMoney;

