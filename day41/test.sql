CREATE TABLE test(
  name CHAR(20),
  kecheng CHAR(20),
  fenshu CHAR(20)
);

INSERT INTO test VALUES('张三','语文',81),
('张三','数学',75),
('李四','语文',76),
('李四','数学',90),
('王五','语文',81),
('王五','数学',82);

select *
from test;
#todo --请用一条Sql语句查处分数大于80的学生

select *
from test where fenshu>80;

select name ,fenshu
from test where fenshu>80;

