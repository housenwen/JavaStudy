#todo 商品分类
# 1.分类的ID
# 2.分类的名称
# 3.分类的描述
create table category(
                         cid int primary key auto_increment,
                         cname varchar(100),
                         cdesc varchar(100)
);
insert into category values(null,'手机数码','电子产品，黑马出品');
insert into category values(null,'衣服箱包','江南皮革厂出品');
insert into category values(null,'香烟酒水','黄鹤楼,茅台,二锅头');
insert into category values(null,'酸奶饼干','哇哈哈,蒙牛酸酸乳');
insert into category values(null,'馋嘴零食','瓜子,花生,八宝粥，辣条');

select * from category;


#todo 所有商品
# 1.商品ID
# 2.商品名称
# 3.商品的价格
# 4.生产日期
# 5.商品分类ID

# todo 商品和商品的所属分类：所属关系
create table product(
                        pid int primary key auto_increment,
                        pname varchar(100),
                        price double,
                        pdate timestamp,
                        cno int
);

insert into product values (null,'小米mix9',998,null,1);
insert into product values (null,'锤子手机',2888,null,1);
insert into product values (null,'阿迪王',99,null,2);
insert into product values (null,'老村长',88,null,3);
insert into product values (null,'劲酒',35,null,3);
insert into product values (null,'小熊饼干',1,null,4);
insert into product values (null,'威龙辣条',1,null,5);
insert into product values (null,'旺旺饼干',1,null,5);

# todo 查询所有商品
select * from product;
# todo 给product 中的这个cno添加一个外键约束
alter table product add foreign key (cno) references category(cid);

# todo 从分类中，删除分类为5信息
delete from category where cid = 5;
# todo 首先得去product表，删除所有分类id 5 商品

#todo 建数据库原则：
# 通常情况下，一个项目、应用建一个数据库

#todo 多表之间的建表原则
# 1.一对多 ： 商品和分类
# 建表原则：在多的一方，添加一个外键指向一的一方的主键
# 2.多对多 ：老师和学生，学生和课程
# 建表原则：建立一个中间表，将多对多的关系，拆分成一对多的关系。
# 中间表至少要有两个外键。
# 3.一对一 ：公民和身份证。班级和班长。国家和国旗。
# 建表原则:
# 将一对一的情况，当作是一对多情况处理，在任意一张表添加一个外键，并且这个外键要唯一，指向另外一张表。
# 直接将两张表合并成一张表。
# 将两张表的主键建立起连接，让两个表里面主键相等。
# 实际用途：用的不是很多。（拆表操作）
# 拆表操作：将个人不常用的信息和常用的信息分离，减少表的臃肿。

#todo 用户表(ID，用户名，密码，手机)

create table user(
    uid int primary key auto_increment,
    username varchar(31),
    password varchar(31),
    phone varchar(11)
);

insert into user values (null,'make','123','13567865679');

# todo 订单表（订单编号，总价，订单时间，地址，外键用户ID）
create table orders(
    oid int primary key auto_increment,
    sum int not null ,
    otime timestamp,
    address varchar(100),
    uno int,
    foreign key (uno) references user(uid)

);

# todo 给orders 中的这个uno添加一个外键约束
alter table orders add foreign key (uno) references user(uid);

insert into orders values (null,200,null,'黑马宿舍',1);
insert into orders values (null,250,null,'黑马前台',1);

# todo 商品表（商品id，商品名称，商品价格，外键cno）
create table product(
    pid int primary key auto_increment,
    pname varchar(100),
    price double,
    cno int,
    foreign key (cno) references category(cid)

);
insert into product values (null,'小米mix9',998,1);
insert into product values (null,'锤子手机',2888,1);
insert into product values (null,'阿迪王',99,2);
insert into product values (null,'老村长',88,3);
insert into product values (null,'劲酒',35,3);
insert into product values (null,'小熊饼干',1,4);
insert into product values (null,'威龙辣条',1,5);
insert into product values (null,'旺旺饼干',1,5);

# todo 商品分类表，（分类ID，分类名称，分类描述）
create table category(
    cid int primary key auto_increment,
    cname varchar(18),
    cdesc varchar(100)

);
insert into category values(null,'手机数码','电子产品，黑马出品');
insert into category values(null,'衣服箱包','江南皮革厂出品');
insert into category values(null,'香烟酒水','黄鹤楼,茅台,二锅头');
insert into category values(null,'酸奶饼干','哇哈哈,蒙牛酸酸乳');
insert into category values(null,'馋嘴零食','瓜子,花生,八宝粥，辣条');

# todo 订单项：中间表，（订单ID，商品ID，商品数量，订单项总价）

create table orderItem(
    ono int,
    pno int,
    foreign key (ono) references orders(oid),
    foreign key (pno) references product(pid),
    ocount  int,
    subsum double

);
# todo 给1号订单添加商品，200块钱的商品
insert into orderItem values (1,7,'100',100);
insert into orderItem values (1,8,'100',100);

# todo 给2好订单添加商品 250元商品
insert into orderItem values (2,5,1,35);
insert into orderItem values (2,3,3,99);

#todo 交叉连接查询 笛卡尔积：查出来的是两张表的乘积。
select *
from product,category;
# todo 过滤出有意义的数据：
select *
from product p,category c where p.cno = c.cid ;

#todo 分页查询：
# 第一个参数是索引
# 第二个参数是显示的个数。
# 每页显示3条数据
# startIndex = (index-1)*3

select *from product limit 0,3;
select *from product limit 3,3;

#todo 子查询
# 查询分类名称为手机数码的商品
# 查询手机数码的ID
select cid from category where cname = '手机数码';

# todo 用ID查询结果。
select *
from product where cno = (select cid from category where cname = '手机数码');







