


create table user(
                     id varchar(18) primary key,
                     user_name varchar(20) not null,
                     telephone varchar(20),
                     address varchar(256)
);

create table account(
                        account_id varchar(20) primary key,
                        user_id varchar(18),
                        type varchar(10),
                        balance double,
                        constraint fk_acc_user foreign key(user_id) references user(id)
);

create table transaction_details(
                                    tr_id varchar(30) primary key,
                                    tr_type varchar(10),
                                    tr_acc_id varchar(20),
                                    tr_oppo_id varchar(20),
                                    tr_time Date,
                                    tr_amount double,
                                    constraint fk_tr_acc1 foreign key(tr_acc_id) references account(account_id),
                                    constraint fk_tr_acc2 foreign key(tr_oppo_id) references account(account_id)
);

-- 插入测试数据
INSERT INTO `user` VALUES ('1', '小明', '133808029292', '上海');
INSERT INTO `user` VALUES ('2', '小红', '135885808080', '苏州');

INSERT INTO `account` VALUES ('1', '1', '普通卡', 1000);
INSERT INTO `account` VALUES ('2', '1', '白金卡', 50000);
INSERT INTO `account` VALUES ('3', '2', '普通卡', 3000);

INSERT INTO `transaction_details` VALUES ('1', '网银', '1', '3', '2020-11-10', 300);
INSERT INTO `transaction_details` VALUES ('2', '手机银行', '3', '1', '2020-05-03', 500);
INSERT INTO `transaction_details` VALUES ('3', '网银', '3', '2', '2020-04-03', 300);





# 1)完成客户，账户，交易明细信息的录入功能；
# 2）查询客户A有哪些账户信息？
# 3）查询客户A在2020年的支出总金额；
# 4）查询客户信息，并封装成客户类返回；