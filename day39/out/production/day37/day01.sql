

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

#todo 查询所有商品
select * from product;
#todo 查询商品名称和商品价格
select pname,price from product;

