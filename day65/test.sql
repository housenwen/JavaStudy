create table user(
                     user_id int primary key auto_increment,
                     user_name varchar(30)
);

create table role(
                     role_id int primary key auto_increment,
                     role_name varchar(30)
);

create table access(
                       access_id int primary key auto_increment,
                       access_name varchar(50)
);

create table user_role_tb(
                             user_id int,
                             role_id int,
                             constraint fk_user foreign key(user_id) references user(user_id),
                             constraint fk_role foreign key(role_id) references role(role_id)
);

create table role_access_tb(
                               role_id int,
                               access_id int,
                               constraint fk_role2 foreign key(role_id) references role(role_id),
                               constraint fk_access foreign key(access_id) references access(access_id)
);

INSERT INTO `user` VALUES (1, '张三');
INSERT INTO `user` VALUES (2, '李四');
INSERT INTO `user` VALUES (3, '小明');

INSERT INTO `role` VALUES (1, '超级管理员');
INSERT INTO `role` VALUES (2, '高级用户');
INSERT INTO `role` VALUES (3, '一般用户');
INSERT INTO `role` VALUES (4, '新手用户');
INSERT INTO `role` VALUES (5, '小黑屋');
INSERT INTO `role` VALUES (6, '测试1');

INSERT INTO `access` VALUES (1, 'root管理员');
INSERT INTO `access` VALUES (2, '管理员');
INSERT INTO `access` VALUES (3, '增');
INSERT INTO `access` VALUES (4, '删');
INSERT INTO `access` VALUES (5, '改');
INSERT INTO `access` VALUES (6, '查');
INSERT INTO `access` VALUES (7, '聊天');
INSERT INTO `access` VALUES (8, '无权限');

INSERT INTO `role_access_tb` VALUES (1, 1);
INSERT INTO `role_access_tb` VALUES (1, 2);
INSERT INTO `role_access_tb` VALUES (1, 3);
INSERT INTO `role_access_tb` VALUES (1, 4);
INSERT INTO `role_access_tb` VALUES (1, 5);
INSERT INTO `role_access_tb` VALUES (1, 6);
INSERT INTO `role_access_tb` VALUES (1, 7);
INSERT INTO `role_access_tb` VALUES (2, 2);
INSERT INTO `role_access_tb` VALUES (2, 3);
INSERT INTO `role_access_tb` VALUES (2, 4);
INSERT INTO `role_access_tb` VALUES (2, 5);
INSERT INTO `role_access_tb` VALUES (2, 6);
INSERT INTO `role_access_tb` VALUES (2, 7);
INSERT INTO `role_access_tb` VALUES (3, 3);
INSERT INTO `role_access_tb` VALUES (3, 4);
INSERT INTO `role_access_tb` VALUES (3, 5);
INSERT INTO `role_access_tb` VALUES (3, 6);
INSERT INTO `role_access_tb` VALUES (3, 7);
INSERT INTO `role_access_tb` VALUES (4, 6);
INSERT INTO `role_access_tb` VALUES (4, 7);
INSERT INTO `role_access_tb` VALUES (5, 7);

INSERT INTO `user_role_tb` VALUES (1, 1);
INSERT INTO `user_role_tb` VALUES (1, 3);
INSERT INTO `user_role_tb` VALUES (1, 4);
INSERT INTO `user_role_tb` VALUES (2, 1);
INSERT INTO `user_role_tb` VALUES (2, 2);
INSERT INTO `user_role_tb` VALUES (2, 4);
INSERT INTO `user_role_tb` VALUES (2, 5);


#todo 1) 搭建Mybatis工程环境，完成相关配置,实现查询所有用户功能；


select *
from user ;



#todo 2）添加姓名为王五的用户,并分配id为1和6的角色;
# 要求:1)封装添加用户接口,并实现主键回填;
# 2)封装接口实现给指定用户批量添加角色功能(使用foreach批量插入);
# 3)将上述两个接口在一个事务下调用实现;



insert into user values(null,'王五');

insert into user_role_tb values (?,?) , (?,?);


#todo 3）查询角色为超级管理员的用户有哪些？


select user.*
from user
         inner join user_role_tb urt on user.user_id = urt.user_id
         inner join role r on urt.role_id = r.role_id
where role_name = '超级管理员';

select
    user.*
from user,
     user_role_tb as ur,
     role
where user.user_id = ur.user_id
  and ur.role_id = role.role_id
  and role.role_name = '超级管理员';



#todo 4）查询张三有哪些角色以及权限信息；
# 	要求:1)查询结果返回User对象;
# 2)将角色集合封装到User下;
# 3)将权限集合封装到User下;

select user.*, r.*, a.*
from user
         inner join user_role_tb urt on user.user_id = urt.user_id
         inner join role r on urt.role_id = r.role_id
         inner join role_access_tb rat on r.role_id = rat.role_id
         inner join access a on rat.access_id = a.access_id
where user.user_name = '张三';


select
    user.*,
    role.*,
    access.*
from user,
     user_role_tb as ur,
     role,
     role_access_tb as ra,
     access
where user.user_id = ur.user_id
  and ur.role_id = role.role_id
  and role.role_id = ra.role_id
  and ra.access_id = access.access_id
  and user.user_name = '张三';















