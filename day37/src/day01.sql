

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

