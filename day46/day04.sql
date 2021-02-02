-- 1. 准备表
CREATE TABLE user(
                     id INT,
                     username VARCHAR(32),
                     password VARCHAR(32),
                     sex VARCHAR(6),
                     email VARCHAR(50)
);
-- 2. 创建存储过程，实现批量插入记录
DELIMITER $$ -- 声明存储过程的结束符号为$$
-- 可以将下面的存储过程理解为java中的一个方法，插入千万条数据之后，在调用存储过程
CREATE PROCEDURE auto_insert()
BEGIN
    DECLARE i INT DEFAULT 1;
    START TRANSACTION; -- 开启事务
    WHILE(i<=10000000)DO
            INSERT INTO user VALUES(i,CONCAT('jack',i),MD5(i),'male',CONCAT('jack',i,'@itcast.cn'));
            SET i=i+1;
        END WHILE;
    COMMIT; -- 提交
END$$ -- 声明结束
DELIMITER ; -- 重新声明分号为结束符号
-- 3. 查看存储过程
SHOW CREATE PROCEDURE auto_insert;
-- 4. 调用存储过程
CALL auto_insert();