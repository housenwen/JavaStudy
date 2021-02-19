create table books
(
    no       int primary key auto_increment,
    name     varchar(50) not null,
    author   varchar(8),
    cno      int(5),
    category varchar(20),
    price    numeric(8),
    constraint fk_book foreign key (cno) references book_concern (cno)

);

create table book_concern
(
    cno   int primary key auto_increment,
    name  varchar(20),
    phone varchar(15),
    city  varchar(20)

);

#todo
# 1.查询出版过“计算机”类图书的出版社编号（若一个出版社出版过多部“计算机”类图书，则在查询结果 中该出版社编号只显示一次）
# 2.查询南开大学出版社的“经济”类或“数学”类图书的信息。
# 3.查询编号为“20001”的出版社出版图书的平均价格
# 4.查询至少出版过20套图书的出版社，在查询结果中按出版社编号的升序顺序显示满足条件的出版社编 号、出版社名称和每个出版社出版的图书套数。
# 5.查询比编号为“20001”的出版社出版图书套数多的出版社编号



