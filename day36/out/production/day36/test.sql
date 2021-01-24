
create table product(
    id int primary key auto_increment,
    name varchar(10),
    price double,
    date timestamp,
    cno int
);

insert into product values (null,'哇哈哈',9,null,1);
insert into product values (null,'苹果9',8988,null,1);
insert into product values (null,'墨镜',98,null,2);
insert into product values (null,'手表',288,null,3);
insert into product values (null,'键盘',18,null,3);
insert into product values (null,'辣条',8,null,4);
insert into product values (null,'饼干',8,null,4);


select *
from product;
select name,price from product;

select name as 商品名称,price as 商品价格 from product;

select price from product;
select distinct price from product;

select *,price*1.5 as 折后价 from product;

select * from product where price > 60 ;

select *
from product where price>10 and price<100;

select *
from product where price between 10 and 100;

select *
from product where name like '饼%';
select *
from product where name like '饼_';

select *
from product where cno in (1,4,5);

select *
from product order by price desc;

select sum(price)
from product;

select avg(price) as 平均价格
from product;

select count(*) as 商品个数
from product;

# 查出所有商品价格大于平均价格的商品

select *
from product where price>513.87;
select *
from product where price>(select avg(price)from product);

select cno,count(*)
from product group by cno;


#todo 2.根据cno分组，分组统计每组商品的平均价格，平且商品的平均价格>60
# having 关键字 可以接聚合函数， 出现在分组之后
select cno,avg(price)
from product group by cno
having avg(price) > 60 ;




