
#todo 按照业务需求定义商家表,用户表,线路表,线路分类和收藏表(SqlYog可视化操作导出SQL或手写DDL皆可)（10分）
create database travel ;

use travel;
# 用户表
create table tab_user(
    id bigint(18) not null primary key auto_increment,
    real_name varchar(200) not null ,
    telephone varchar(12) not null ,
    sex char(200) ,
    username varchar(100) not null ,
    password varchar(32) not null ,
    birthday datetime ,
    email varchar(100)
);
# 路线表
create table tab_route(
                          id bigint(18) not null primary key auto_increment,
                          route_name varchar(200) not null ,
                          price decimal(12,4) not null ,
                          route_Introduce varchar(2000) ,
                          flag char(1) not null ,
                          is_theme_tour char(1) not null ,
                          attention_count int(11) not null ,
                          category_id bigint(18) not null ,
                          seller_id bigint(18) not null,
                          constraint foreign key (category_id) references tab_category(id),
                          constraint foreign key (seller_id) references tab_seller(id)
);
# 收藏表
create table tab_favorite(
    id bigint(18) not null primary key auto_increment,
    user_id bigint(18) not null,
    route_id bigint(18) not null,
    constraint foreign key (user_id) references tab_user(id),
    constraint foreign key (route_id) references tab_route(id)
);
# 分类表
create table tab_category(
                             id bigint(18) not null primary key,
                             category_name varchar(100) not null ,
                             category_url varchar(255) not null
);
# 商家表
create table tab_seller(
                           id bigint(18) not null primary key auto_increment,
                           seller_name varchar(200) not null ,
                           telephone varchar(12) not null ,
                           address varchar(200),
                           username varchar(100) not null ,
                           password varchar(32) not null ,
                           birthday datetime
);

# 给用户表中telephone添加唯一约束(SqlYog可视化操作导出SQL或手写DDL皆可)(5分)

ALTER TABLE tab_user ADD UNIQUE (telephone);

# 使用SQL向用户表全字段批量插入2条测试数据 (5分)

insert into tab_user values (null,'张三','138123456789','男','zhangsan','123456',null,null);
insert into tab_user values (null,'李四','139123456789','男','lisi','111111',null,null);

# 使用SQL完成user_id为2的用户对route_id为2的线路的收藏(insert语句)与取消收藏(delete语句)功能(5分)

insert into tab_favorite values (null,2,2);

delete from tab_favorite where user_id = 2;


# 使用select语句查询姓名为'A'的用户收藏过哪些旅游线路(显示线路的所有字段信息)(5分)

select tab_user.real_name,tr.*
from tab_user
         inner join tab_favorite tf on tab_user.id = tf.user_id
         inner join tab_route tr on tf.route_id = tr.id
where username = 'A';

# 使用SQL统计每个线路分类下各有多少旅游线路数量(显示线路分类名称,数量)(5分)

select tab_category.id,count(tr.id)
from tab_category inner join tab_route tr on tab_category.id = tr.category_id
group by category_id;

# 使用SQL统计每个线路分类下用户的收藏数量,并倒叙排序(查询字段包含线路分类名称,数量)(5分)

select tc.category_name,count(*) as c
from tab_user inner join tab_favorite tf on tab_user.id = tf.user_id
inner join tab_route tr on tf.route_id = tr.id
inner join tab_category tc on tr.category_id = tc.id
group by tc.id order by c desc
;

# 补充:线路分类下对应的线路收藏数如果为0,则不显示;

select tc.category_name,count(*) as c
from tab_user inner join tab_favorite tf on tab_user.id = tf.user_id
              inner join tab_route tr on tf.route_id = tr.id
              inner join tab_category tc on tr.category_id = tc.id
group by tc.id order by c desc ;


# 使用SQL查询id为2的用户收藏过哪些旅游线路,包含线路表所有字段,线路分类名称和商家电话(5分)；

select tr.*,tc.category_name as categoryName,ts.telephone as tel
from tab_user inner join tab_favorite tf on tab_user.id = tf.user_id
inner join tab_route tr on tf.route_id = tr.id
inner join tab_category tc on tr.category_id = tc.id
inner join tab_seller ts on tr.seller_id = ts.id
where tab_user.id = 2 ;

# 使用SQL根据商家名称(比如seller_name = 'it黑马')查询其发布的旅游线路有哪些用户收藏(显示用户表所有字段信息)(5分)

select ts.seller_name as sName ,tc.category_name as cName,tab_user.*
from tab_user inner join tab_favorite tf on tab_user.id = tf.user_id
              inner join tab_route tr on tf.route_id = tr.id
              inner join tab_category tc on tr.category_id = tc.id
              inner join tab_seller ts on tr.seller_id = ts.id
where ts.seller_name = 'it黑马'
;



